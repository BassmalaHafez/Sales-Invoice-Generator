package InvoiceOperations;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceOperation {

    public static ArrayList<InvoiceHeader> readFile() {
        ArrayList<InvoiceHeader> invoices = new ArrayList<>();
        String invoiceHeaderFilePath = "Resources\\InvoiceLine.csv";
        String invoiceLineFilePath = "Resources\\InvoiceLine.csv";
        BufferedReader reader = null;
        String invoiceLine;
        try {
            reader = new BufferedReader(new FileReader(invoiceHeaderFilePath));
            if ((invoiceLine = reader.readLine()) != null) {
                InvoiceHeader invoice;
                String[] row = invoiceLine.split(",");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = sdf.parse(row[1]);
                invoice = new InvoiceHeader(Integer.parseInt(row[0]), date, row[2]);
                ArrayList<InvoiceLine> invoiceItems = getItemsForInvoice(invoice, invoiceLineFilePath);
                invoice.setInvoiceLines(invoiceItems);
                invoices.add(invoice);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("This file is not found :(");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return invoices;
    }

    public static ArrayList<InvoiceHeader> readFile(String invoicesFilePath, String itemsFilePath) {
        ArrayList<InvoiceHeader> invoices = new ArrayList<>();

        BufferedReader reader = null;
        String invoiceLine;
        try {
            reader = new BufferedReader(new FileReader(invoicesFilePath));
            while ((invoiceLine = reader.readLine()) != null) {
                InvoiceHeader invoice;
                String[] row = invoiceLine.split(",");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = sdf.parse(row[1]);
                invoice = new InvoiceHeader(Integer.parseInt(row[0]), date, row[2]);
                ArrayList<InvoiceLine> invoiceItems = getItemsForInvoice(invoice, itemsFilePath);
                invoice.setInvoiceLines(invoiceItems);
                invoices.add(invoice);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("This file is not found ");

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return invoices;
    }

    public static void writeFile(ArrayList<InvoiceHeader> invoices) {

        String invoiceHeaderFilePath = "Resources\\Invoices.csv";
        String invoiceLineFilePath = "Resources\\InvoiceItems.csv";
        PrintWriter invoiceHeaderWriter = null;
        PrintWriter invoiceLineWriter = null;

        try {

            invoiceHeaderWriter = getFileWriter(invoiceHeaderFilePath);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (InvoiceHeader invoice : invoices) {
                String formattedDate = sdf.format(invoice.getInvoiceDate());
                invoiceHeaderWriter.println(invoice.getInvoiceNum() + "," + formattedDate + "," + invoice.getCustomerName());
            }

            invoiceHeaderWriter.flush();

            invoiceLineWriter = getFileWriter(invoiceLineFilePath);
            ArrayList<InvoiceLine> invoiceItems;

            for (InvoiceHeader invoice : invoices) {
                invoiceItems = invoice.getInvoiceLines();
                for (InvoiceLine item : invoiceItems) {
                    invoiceLineWriter.println(invoice.getInvoiceNum() + "," + item.getItemName() + ","
                            + String.valueOf(item.getItemPrice()) + "," + String.valueOf(item.getCount()));
                }

            }

            invoiceLineWriter.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            invoiceHeaderWriter.close();
            invoiceLineWriter.close();
        }

    }

    public static void writeFile(ArrayList<InvoiceHeader> invoices, String invoicesFilePath, String itemsFilePath) {

        PrintWriter invoiceHeaderWriter = null;
        PrintWriter invoiceLineWriter = null;

        try {

            invoiceHeaderWriter = getFileWriter(invoicesFilePath);
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            for (InvoiceHeader invoice : invoices) {
                String formattedDate = sdf.format(invoice.getInvoiceDate());
                invoiceHeaderWriter.println(invoice.getInvoiceNum() + "," + formattedDate + "," + invoice.getCustomerName());
            }

            invoiceHeaderWriter.flush();

            invoiceLineWriter = getFileWriter(itemsFilePath);
            ArrayList<InvoiceLine> invoiceItems;

            for (InvoiceHeader invoice : invoices) {
                invoiceItems = invoice.getInvoiceLines();
                for (InvoiceLine item : invoiceItems) {
                    invoiceLineWriter.println(invoice.getInvoiceNum() + "," + item.getItemName() + ","
                            + String.valueOf(item.getItemPrice()) + "," + String.valueOf(item.getCount()));
                }

            }

            invoiceLineWriter.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            invoiceHeaderWriter.close();
            invoiceLineWriter.close();
        }

    }


    private static ArrayList<InvoiceLine> getItemsForInvoice(InvoiceHeader invoice, String filePath) {
        ArrayList<InvoiceLine> invoiceItems = new ArrayList<>();
        BufferedReader reader = null;
        String invoiceLine;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            if ((invoiceLine = reader.readLine()) != null) {
                String[] row = invoiceLine.split(",");
                int num = Integer.parseInt(row[0]);
                if (num == invoice.getInvoiceNum()) {
                    invoiceItems.add(new InvoiceLine(invoice, row[1],
                            Double.parseDouble(row[2]), Integer.parseInt(row[3])));
                }

            }
        }
        catch (FileNotFoundException e) {
            e = new FileNotFoundException("This file is not found ");
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        return invoiceItems;
    }


    private static PrintWriter getFileWriter(String filePath) throws IOException {

        return new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
    }

}
