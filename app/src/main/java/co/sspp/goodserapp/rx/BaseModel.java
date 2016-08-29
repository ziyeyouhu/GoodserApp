package co.sspp.goodserapp.rx;

import java.io.Serializable;

/**
 * = on 16-8-12
 * =
 */
public class BaseModel<T> implements Serializable{
    public int RetCode;
    public String RetMsg;
    public T RetData;


    public boolean success(){
        return RetCode==0;
    }
}
