package BPMS;

public class HealthMonitoringApp 
{

    static BPBook bpBook;
    static BPMSGui gui;

    public static void main(String[] args) 
    {
        bpBook = new BPBook();
        gui = new BPMSGui(bpBook);

        TCPServerBPMS s = new TCPServerBPMS(8080 /*Integer.parseInt(args[2])*/ );
        s.start();
    }
  
    public static void setBPBook(BPBook bpBook2) 
    {
        bpBook = bpBook2;
        gui.setBpBook(bpBook);
    }
	

}
