package nnero.ncrawler;

import com.google.common.base.Strings;
import nnero.ncrawler.annotation.AnnoEntity;
import nnero.ncrawler.annotation.ExtractBy;
import nnero.ncrawler.annotation.HelpUrl;
import nnero.ncrawler.annotation.TargetUrl;
import nnero.ncrawler.entity.AnnoResults;
import nnero.ncrawler.entity.Page;
import nnero.ncrawler.entity.Results;
import nnero.ncrawler.entity.anno.AnnotationEntity;
import nnero.ncrawler.pipeline.Pipeline;
import nnero.ncrawler.util.MD5;
import nnero.ncrawler.util.NLog;
import nnero.ncrawler.util.Pair;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/1 下午5:47
 * <p>
 * Function:Annotation type crawler
 *          this class suites just extract content but do not doing too many operations.
 *          note: addStartLink() won't work in this class.
 *          @since 0.2.0
 * <p>
 * ************************************************
 */
public class AnnoCrawler<T> extends BaseCrawler {

    private Class<T> mClazz;
    private String mTargetUrlRegex;
    private String mHelpUrlRegex;

    private String[] mKeys;//field names

    //key : field name. value: regex and group
    private Map<String,Pair<String,Integer>> mMap = new HashMap<>();
    //key :field name. value:backup regex and group.
    //this field is created if backup keys exists.
    private Map<String,Pair<String,Integer>> mBackupMap;

    public AnnoCrawler(Class<T> clazz) {
        super();
        this.mClazz = clazz;
        mTaskId = "AnnoCrawler:"+ MD5.generate(this.toString());
        findAnnotations();
    }

    /**
     * create annoCrawler
     * @since 0.2.0
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static AnnoCrawler create(Class<?> clazz){
        return new AnnoCrawler(clazz);
    }

    @Override
    public String getTaskID() {
        return mTaskId;
    }

    @Override
    public void run() {
        NLog.trace("Anno Run");
        super.run();
    }

    //process page's result in annotation ways.
    @Override
    protected void process(Page page){
        try {
            List<T> objs = new ArrayList<>();
            for(String key:mKeys) {
                Pair<String,Integer> pair = mMap.get(key);
                List<String> contents = page.getHtml().regex(pair.l,pair.r).all();
                if(mBackupMap != null && mBackupMap.get(key) != null && contents.size() == 0){
                    Pair<String,Integer> backupPair = mBackupMap.get(key);
                    contents.addAll(page.getHtml().regex(backupPair.l,backupPair.r).all());
                }
                for(int i=0;i<contents.size();i++){
                    T o = null;
                    if(objs.size() <= i) {
                        o = mClazz.newInstance();
                        objs.add(o);
                    } else {
                        o = objs.get(i);
                    }
                    Field f = mClazz.getDeclaredField(key);
                    f.setAccessible(true);
                    f.set(o, contents.get(i));
                }
            }
            page.setAnnoResults(new AnnoResults<T>(objs));
            if(!Strings.isNullOrEmpty(mTargetUrlRegex))
                page.addTargetUrls(page.getHtml().links().regex(mTargetUrlRegex).all());
        } catch (InstantiationException e) {
            NLog.fatal("instant mClazz instance fail.");
        } catch (IllegalAccessException e) {
            NLog.fatal("access exception!");
        } catch (NoSuchFieldException e) {
            NLog.fatal("no such field:"+e.getMessage());
        }
    }

    private void findAnnotations(){
        if(mClazz == null)
            throw new RuntimeException("entity's class is null");

        AnnoEntity parentAnno = mClazz.getAnnotation(AnnoEntity.class);
        if(parentAnno == null)
            throw new RuntimeException("object class must extends AnnoEntity");

        //parse class annotations
        Annotation[] annotations = mClazz.getAnnotations();
        for(Annotation a:annotations){
            if(a instanceof TargetUrl){
                TargetUrl targetUrl = (TargetUrl) a;
                mTargetUrlRegex = targetUrl.value();
            } else if(a instanceof HelpUrl){
                HelpUrl helpUrl = (HelpUrl) a;
                mHelpUrlRegex = helpUrl.value();
            }
        }

        //parse field annotations
        Field[] fields = mClazz.getDeclaredFields();
        mKeys = new String[fields.length];
        for(int i=0;i<fields.length;i++){
            ExtractBy extractBy = fields[i].getAnnotation(ExtractBy.class);
            if(extractBy != null) {
                mKeys[i] = fields[i].getName();
                mMap.put(fields[i].getName(), new Pair<>(extractBy.regex(), extractBy.group()));
                if (!Strings.isNullOrEmpty(extractBy.backup())) {
                    if(mBackupMap == null){
                        mBackupMap = new HashMap<>();
                    }
                    mBackupMap.put(fields[i].getName(),
                            new Pair<>(extractBy.backup(), extractBy.backGroup()));
                }
            }
        }
    }
}
