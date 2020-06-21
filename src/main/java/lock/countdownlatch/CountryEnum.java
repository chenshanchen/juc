package lock.countdownlatch;

/**
 * 枚举类 相当于一个 数据类型的数据库
 */
public enum CountryEnum {


   ONE(1,"齐"),
    TWO(2,"楚"),
    THREE(3,"燕"),
    FOUR(4,"赵"),
    FIVE(5,"魏"),
    SIX(6,"韩");


    private Integer retCode;
    private String retMessage;

    public Integer getRetCode() {
        return retCode;
    }

    public void setRetCode(Integer retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    CountryEnum(Integer retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    //遍历获取枚举中的某个值
    public static CountryEnum ForEach_CountryEnum(int index){
        CountryEnum[] enums = CountryEnum.values();
        for (CountryEnum countryEnum:enums) {
            if(countryEnum.retCode == index){
                return countryEnum;
            }
        }
        return null;
    }
}
