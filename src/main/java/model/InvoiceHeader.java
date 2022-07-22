package InvoiceOperations;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    // Declare Fields
    private int invoiceNum;
    private Date invoiceDate;
    private String customerName;
    private ArrayList<InvoiceLine> invoiceLines;

    //create invoice header Constructor

    public InvoiceHeader(int invoiceNum, Date invoiceDate, String customerName) {
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
    }

    //Create Getter Methods

    public int getInvoiceNum() {
        return invoiceNum;
    }
    public Date getInvoiceDate() {
        return invoiceDate;
    }
    public String getCustomerName() {
        return customerName;
    }
    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }


    // Create Setter Methods
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public void setInvoiceNum(int invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        this.invoiceLines = invoiceLines;
    }


    // toString method returns the String representation of the object
    public String toString() {
        SimpleDateFormat sampleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String date = sampleDateFormat.format(invoiceDate);
        return "InvoiceHeader{" + "invoiceNum=" + invoiceNum + ", invoiceDate=" + date + ", customerName=" + customerName + ",\ninvoiceLines=" + invoiceLines + '}';
    }
}
