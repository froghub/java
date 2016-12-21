package local.shop.model.deprecated;

import javafx.beans.property.SimpleStringProperty;

public class ArchivedCheck extends ItemCheck   {


    String checkDate;

    public ArchivedCheck(){}

    public ArchivedCheck(int id, int size, float sum, String checkDate){
        super(id,size,sum);
        this.checkDate=checkDate;
    }

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }
    public SimpleStringProperty dateProperty() {
        return new SimpleStringProperty(checkDate);
    }


}
