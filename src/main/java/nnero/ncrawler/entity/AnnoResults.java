package nnero.ncrawler.entity;

import java.util.List;

/**
 * **********************************************
 * <p>
 * Author NNERO
 * <p>
 * Time : 16/4/5 下午2:50
 * <p>
 * Function:Anno's results is always a list.
 *          @since 0.2.0
 * <p>
 * ************************************************
 */
public class AnnoResults<T> {

    private List<T> objects;

    public AnnoResults(List<T> objects){
        this.objects = objects;
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }
}
