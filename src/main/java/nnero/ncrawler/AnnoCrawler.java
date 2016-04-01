package nnero.ncrawler;

import nnero.ncrawler.annotation.AnnoEntity;
import nnero.ncrawler.annotation.ExtractBy;
import nnero.ncrawler.annotation.HelpUrl;
import nnero.ncrawler.annotation.TargetUrl;
import nnero.ncrawler.entity.AnnotationEntity;
import nnero.ncrawler.proccessor.PageProcessor;
import nnero.ncrawler.util.MD5;
import nnero.ncrawler.util.NLog;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;

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
 * <p>
 * ************************************************
 */
public class AnnoCrawler extends BaseCrawler {

    private AnnotationEntity mEntity;

    public AnnoCrawler(AnnotationEntity entity) {
        super();
        this.mEntity = entity;
        mTaskId = "AnnoCrawler:"+ MD5.generate(this.toString());
        findAnnotations();
    }

    public static AnnoCrawler create(AnnotationEntity entity){
        return new AnnoCrawler(entity);
    }

    @Override
    public String getTaskID() {
        return mTaskId;
    }

    @Override
    public void run() {
        NLog.d("Anno Run");
    }

    private void findAnnotations(){
        if(mEntity == null)
            throw new RuntimeException("entity is null");

        Class<?> parentClass = mEntity.getClass();
        AnnoEntity parentAnno = parentClass.getAnnotation(AnnoEntity.class);
        if(parentAnno == null)
            throw new RuntimeException("entity must extends AnnoEntity");

        Annotation[] annotations = parentClass.getAnnotations();
        for(Annotation a:annotations){
            if(a instanceof TargetUrl){
                TargetUrl targetUrl = (TargetUrl) a;
                NLog.d(targetUrl.value());
            } else if(a instanceof HelpUrl){
                HelpUrl helpUrl = (HelpUrl) a;
                NLog.d(helpUrl.value());
            }
        }

        Field[] fields = parentClass.getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            ExtractBy extractBy = f.getAnnotation(ExtractBy.class);
            NLog.d(extractBy.regex());
        }
    }
}
