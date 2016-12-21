package local.shop.model.deprecated;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Waybill {

    private int waybillId;
    private int sum;
    private String createDate;
    private boolean isReceived;
    private String receivedDate;
    private ObservableList<Entry> items = FXCollections.observableArrayList();

    Waybill(){}


    class Entry {
        private int id;
        private int itemId;
        private float price;
        private int count;
        //private int shipper; // or class Shipper ?
        private Shipper shipper;

        public Entry(){}
        public Entry(int itemId, float price, int count, Shipper shipper) {
            this.itemId = itemId;
            this.price = price;
            this.count = count;
            this.shipper = shipper;
        }
    }

    public void addItem(){

    }


}
