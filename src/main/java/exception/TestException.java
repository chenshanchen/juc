package exception;


import org.junit.jupiter.api.Test;

import java.text.MessageFormat;

public class TestException {

    @Test
    public void test1(){
        String message = "读入你要搜索的内容";
        boolean f = false;
        try {
            MessageFormat format = new MessageFormat(message);
            f = format.getFormats().length>0;
        }catch (IllegalArgumentException e){
            System.out.println("错误的描述格式非法");
        }

    }
}
