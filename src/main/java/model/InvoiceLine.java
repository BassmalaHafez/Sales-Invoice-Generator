package InvoiceOperations;

public class InvoiceLine {
    //Declare Fields
    private InvoiceHeader invoice;
    private String itemName;
    private double itemPrice;
    private int count;

    // Create Invoiceline Constructor
    public InvoiceLine(InvoiceHeader invoice, String itemName, double itemPrice, int count) {
        this.invoice = invoice;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.count = count;
    }
    // Create Getter Methods
    public InvoiceHeader getInvoice() {
        return invoice;
    }
    public String getItemName() {
        return itemName;
    }
    public double getItemPrice() {
        return itemPrice;
    }
    public int getCount() {
        return count;
    }


    // Create Setter Methods
    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setCount(int count) {
        this.count = count;
    }

     // toString method returns the String representation of the object
    public String toString() {
        return "\t" + "InvoiceLine{itemName=" + itemName + ", itemPrice=" + itemPrice + ", count=" + count + "}\n\t";
    }
}
