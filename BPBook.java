package BPMS;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ListIterator;

public class BPBook implements Serializable {
   private String fname = null;
   private ArrayList<Measurement> al = new ArrayList();

   public void insertBpm(Measurement a) throws UnknownHostException, IOException 
   {
      this.al.add(a);
      DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss"); 
      String[] toSend = new String[] {Integer.toString(a.getUserId()), dateFormat.format(a.date), Integer.toString(a.systolicBP), Integer.toString(a.diastolicBP), Integer.toString(a.heartRate)};
      TCPClientBPMS.upload(toSend);
   }

   public void print() 
   {
      ListIterator myIt = this.al.listIterator();

      while(myIt.hasNext()) 
      {
         Measurement a = (Measurement)myIt.next();
         a.print();
      }

   }

   public void sort() 
   {
      Collections.sort(this.al, new Comparator<Measurement>() 
      {
         public int compare(Measurement left, Measurement right) 
         {
            return left.getUserId() > right.getUserId() ? 1 : (left.getUserId() < right.getUserId() ? -1 : 0);
         }
      });
   }

   //pass al to stream, filter for any userID like key, else return a null of type Measurement
   public Measurement find(String key) 
   {
      Measurement bpm = (Measurement)this.getAl().stream().filter((bm) -> 
      {return key.equals(Integer.toString(bm.getUserId()));}).findAny().orElse((Measurement)null);
      return bpm;
   }

   public void save(String fname) 
   {
      ObjectOutputStream oos = null;

      try {
         oos = new ObjectOutputStream(new FileOutputStream(new File(fname)));
      } catch (FileNotFoundException var6) {
         var6.printStackTrace();
      } catch (IOException var7) {
         var7.printStackTrace();
      }

      try {
         oos.writeObject(this);
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      try {
         oos.close();
      } catch (IOException var4) {
         var4.printStackTrace();
      }

   }

   public static BPBook load(BPBook bpBook, String fname) 
   {
      ObjectInputStream ois = null;

      try {
         ois = new ObjectInputStream(new FileInputStream(new File(fname)));
      } catch (FileNotFoundException var7) {
         var7.printStackTrace();
      } catch (IOException var8) {
         var8.printStackTrace();
      }

      BPBook bpb = null;

      try {
         bpb = (BPBook)ois.readObject();
      } catch (IOException | ClassNotFoundException var6) {
         var6.printStackTrace();
      }

      try {
         ois.close();
      } catch (IOException var5) {
         var5.printStackTrace();
      }

      return bpb;
   }

   public ArrayList<Measurement> getAl() 
   {
      return this.al;
   }

}
