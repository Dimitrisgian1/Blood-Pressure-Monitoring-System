package BPMS;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.ListIterator;

public class BPMSGui extends Frame {
   private BPBook clientbpBook;
   private int baseY = 140;
   private int baseX = 100;
   private MenuBar mb;
   private Label statusLabel;
   private TextArea ta;
   public TextField fNameTf;
   public TextField userIdtf;
   public TextField systolicBPtf;
   public TextField diastolicBPtf;
   public TextField heartRateTf;
   private Label fNameLabel;
   private Label fNameErrorLabel;
   private Label userIdLabel;
   private Label userIdErrorLabel;
   private Label systolicBPLabel;
   private Label heartRateLabel;
   private Label diastolicBPLabel;

   public BPMSGui(BPBook bpb) {
      super("HealthMonitoringApp V2.1 - Blood Pressure Monitoring System (BPMS)");
      this.clientbpBook = bpb;
      this.setLayout((LayoutManager) null);
      this.setFont(new Font("TimesRoman", 0, 12));
      this.setBackground(Color.cyan);
      this.setSize(new Dimension(660, 580));
      this.setLocation(50, 100);
      this.setVisible(true);
      this.toFront();
      this.setResizable(false);
      this.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent) {
            System.exit(0);
         }
      });
      this.defineMenu();
      this.defineLabels();
      this.ta = new TextArea("", 10, 1, 0);
      this.ta.setBounds(20, this.baseY + 200, 600, 200);
      this.ta.setEditable(false);
      this.ta.setBackground(Color.white);
      this.add(this.ta);
   }

   private void defineMenu() {
      this.mb = new MenuBar();
      Menu msItemMenu = new Menu("Measurement");
      Menu msBookMenu = new Menu("BpBook");
      MenuItem saveMenuItem = new MenuItem("Save", new MenuShortcut(83));
      saveMenuItem.setActionCommand("Save");
      MenuItem clearMenuItem = new MenuItem("Clear");
      clearMenuItem.setActionCommand("Clear");
      MenuItem findMenuItem = new MenuItem("Find", new MenuShortcut(70));
      findMenuItem.setActionCommand("Find");
      MenuItem exitMenuItem = new MenuItem("Exit");
      exitMenuItem.setActionCommand("Exit");
      MenuItem displayMenuItem = new MenuItem("Display");
      displayMenuItem.setActionCommand("DisplayBook");
      MenuItem clearDisplayMenuItem = new MenuItem("Clear Display");
      clearDisplayMenuItem.setActionCommand("ClearDisplay");
      MenuItem sortMenuItem = new MenuItem("Sort");
      sortMenuItem.setActionCommand("SortBook");
      MenuItem saveBookMenuItem = new MenuItem("Save");
      saveBookMenuItem.setActionCommand("SaveBook");
      MenuItem clearBookMenuItem = new MenuItem("Clear BPBook");
      clearBookMenuItem.setActionCommand("ClearBook");
      MenuItem loadBookMenuItem = new MenuItem("Load");
      loadBookMenuItem.setActionCommand("LoadBook");
      BPMSGui.BpmMenuItemListener menuItemListener = new BPMSGui.BpmMenuItemListener();
      BPMSGui.BookMenuItemListener menuBookItemListener = new BPMSGui.BookMenuItemListener();
      saveMenuItem.addActionListener(menuItemListener);
      clearMenuItem.addActionListener(menuItemListener);
      findMenuItem.addActionListener(menuItemListener);
      exitMenuItem.addActionListener(menuItemListener);
      displayMenuItem.addActionListener(menuBookItemListener);
      clearDisplayMenuItem.addActionListener(menuBookItemListener);
      sortMenuItem.addActionListener(menuBookItemListener);
      saveBookMenuItem.addActionListener(menuBookItemListener);
      clearBookMenuItem.addActionListener(menuBookItemListener);
      loadBookMenuItem.addActionListener(menuBookItemListener);
      msItemMenu.add(saveMenuItem);
      msItemMenu.add(clearMenuItem);
      msItemMenu.add(findMenuItem);
      msItemMenu.addSeparator();
      msItemMenu.add(exitMenuItem);
      msBookMenu.add(displayMenuItem);
      msBookMenu.add(sortMenuItem);
      msBookMenu.addSeparator();
      msBookMenu.add(saveBookMenuItem);
      msBookMenu.add(loadBookMenuItem);
      msBookMenu.addSeparator();
      msBookMenu.add(clearDisplayMenuItem);
      msBookMenu.add(clearBookMenuItem);
      this.mb.add(msItemMenu);
      this.mb.add(msBookMenu);
      this.setMenuBar(this.mb);
   }

   private void defineLabels() {
      this.fNameLabel = new Label("BPBook fname");
      this.fNameLabel.setBackground(Color.orange);
      this.fNameLabel.setBounds(this.baseX + 200, this.baseY - 50, 90, 20);
      this.add(this.fNameLabel);
      this.fNameTf = new TextField("");
      this.fNameTf.setEditable(true);
      this.fNameTf.setBounds(this.baseX + 290, this.baseY - 50, 190, 20);
      this.fNameTf.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BPMSGui.this.fNameErrorLabel.setText("");
         }
      });
      this.add(this.fNameTf);
      this.fNameErrorLabel = new Label("");
      this.fNameErrorLabel.setBackground(Color.pink);
      this.fNameErrorLabel.setBounds(this.baseX + 290, this.baseY - 28, 250, 20);
      this.add(this.fNameErrorLabel);
      Button browseB = new Button("Browse");
      browseB.setBounds(this.baseX + 490, this.baseY - 50, 50, 20);
      this.add(browseB);
      browseB.addActionListener(new BPMSGui.BrowseHandler(this));
      this.userIdLabel = new Label("User ID");
      this.userIdLabel.setBackground(Color.orange);
      this.userIdLabel.setBounds(this.baseX, this.baseY, 40, 20);
      this.add(this.userIdLabel);
      this.userIdtf = new TextField("");
      this.userIdtf.setEditable(true);
      this.userIdtf.setBounds(this.baseX + 60, this.baseY, 40, 20);
      this.userIdtf.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            BPMSGui.this.userIdErrorLabel.setText("");
         }
      });
      this.add(this.userIdtf);
      this.userIdErrorLabel = new Label("");
      this.userIdErrorLabel.setBackground(Color.pink);
      this.userIdErrorLabel.setBounds(this.baseX, this.baseY + 22, 350, 20);
      this.add(this.userIdErrorLabel);
      this.systolicBPLabel = new Label("systolicBP");
      this.systolicBPLabel.setBackground(Color.orange);
      this.systolicBPLabel.setBounds(this.baseX, this.baseY + 50, 60, 20);
      this.add(this.systolicBPLabel);
      this.systolicBPtf = new TextField("");
      this.systolicBPtf.setEditable(true);
      this.systolicBPtf.setBounds(this.baseX + 60, this.baseY + 50, 40, 20);
      this.add(this.systolicBPtf);
      this.diastolicBPLabel = new Label("diastolicBP");
      this.diastolicBPLabel.setBackground(Color.orange);
      this.diastolicBPLabel.setBounds(this.baseX, this.baseY + 100, 60, 20);
      this.add(this.diastolicBPLabel);
      this.diastolicBPtf = new TextField("");
      this.diastolicBPtf.setEditable(true);
      this.diastolicBPtf.setBounds(this.baseX + 60, this.baseY + 100, 40, 20);
      this.add(this.diastolicBPtf);
      this.heartRateLabel = new Label("heartRate");
      this.heartRateLabel.setBackground(Color.orange);
      this.heartRateLabel.setBounds(this.baseX, this.baseY + 150, 60, 20);
      this.add(this.heartRateLabel);
      this.heartRateTf = new TextField("");
      this.heartRateTf.setEditable(true);
      this.heartRateTf.setBounds(this.baseX + 60, this.baseY + 150, 40, 20);
      this.add(this.heartRateTf);
   }

   private void findBpMeasurement() {
      Measurement bpm = this.clientbpBook.find(this.userIdtf.getText());
      if (bpm != null) {
         this.displayBp(bpm);
      } else {
         this.userIdErrorLabel.setText("user ID not found");
      }

   }

   private void displayBp(Measurement ui) {
      this.userIdtf.setText(Integer.toString(ui.getUserId()));
      this.systolicBPtf.setText(Integer.toString(ui.systolicBP));
      this.diastolicBPtf.setText(Integer.toString(ui.diastolicBP));
      this.heartRateTf.setText(Integer.toString(ui.heartRate));
   }

   public void setBpBook(BPBook bpBook2) {
      this.clientbpBook = bpBook2;
   }

   class BookMenuItemListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String cmd = e.getActionCommand();
         switch (cmd.hashCode()) {
            case -2008494554:
               if (cmd.equals("SaveBook")) {
                  if (BPMSGui.this.fNameTf.getText().contentEquals("")) {
                     BPMSGui.this.fNameErrorLabel.setText("file name must be specified before saving");
                  } else {
                     BPMSGui.this.clientbpBook.save(BPMSGui.this.fNameTf.getText());
                  }
               }
               break;
            case -913293653:
               if (cmd.equals("DisplayBook")) {
                  this.displayList();
               }
               break;
            case 732534454:
               if (cmd.equals("ClearBook")) {
                  BPMSGui.this.clientbpBook = new BPBook();
                  HealthMonitoringApp.setBPBook(BPMSGui.this.clientbpBook);
               }
               break;
            case 1725958599:
               if (cmd.equals("SortBook")) {
                  BPMSGui.this.clientbpBook.sort();
               }
               break;
            case 1812270549:
               if (cmd.equals("ClearDisplay")) {
                  this.clearDisplay();
               }
               break;
            case 1909716239:
               if (cmd.equals("LoadBook")) {
                  if (BPMSGui.this.fNameTf.getText().contentEquals("")) {
                     BPMSGui.this.fNameErrorLabel.setText("file name must be specified before loading");
                  } else {
                     BPMSGui.this.clientbpBook = BPBook.load(BPMSGui.this.clientbpBook, BPMSGui.this.fNameTf.getText());
                  }
               }
         }

      }

      private void clearDisplay() {
         BPMSGui.this.ta.setText(" ");
      }

      private void displayList() {
         BPMSGui.this.ta.append("userID\t\t|Date\t\t\t\t|systolicBP\t|diastolicBP\t|hartRate\t|\n");
         ListIterator myIt = BPMSGui.this.clientbpBook.getAl().listIterator();

         while (myIt.hasNext()) {
            Measurement a = (Measurement) myIt.next();
            BPMSGui.this.ta.append(a.toString());
         }
      }
   }

   class BpmMenuItemListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         String cmd = e.getActionCommand();
         switch (cmd.hashCode()) {
            case 2174270:
               if (cmd.equals("Exit")) {
                  System.exit(0);
               }
               break;
            case 2189785:
               if (cmd.equals("Find")) {
                  if (BPMSGui.this.userIdtf.getText().contentEquals("")) {
                     BPMSGui.this.userIdErrorLabel.setText("user ID should be correctly given before Find");
                  } else {
                     BPMSGui.this.findBpMeasurement();
                  }
               }
               break;
            case 2569629:
               if (cmd.equals("Save")) {
                  if (BPMSGui.this.userIdtf.getText().contentEquals("")) {
                     BPMSGui.this.userIdErrorLabel.setText("BP Measurement should be correctly given before Saving");
                  } else {
                     try {
                        this.saveBPItem();
                     } catch (UnknownHostException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                     }
                  }
               }
               break;
            case 65193517:
               if (cmd.equals("Clear")) {
                  this.clearBPGui();
               }
         }
      }

      private void clearBPGui() {
         BPMSGui.this.userIdtf.setText("");
         BPMSGui.this.systolicBPtf.setText("");
         BPMSGui.this.diastolicBPtf.setText("");
         BPMSGui.this.heartRateTf.setText("");
      }

      private void saveBPItem() throws UnknownHostException, IOException 
      {
         Measurement bpm = new Measurement(Integer.parseInt(BPMSGui.this.userIdtf.getText()), new Date(), Integer.parseInt(BPMSGui.this.systolicBPtf.getText()), Integer.parseInt(BPMSGui.this.diastolicBPtf.getText()), Integer.parseInt(BPMSGui.this.heartRateTf
               .getText()));
         BPMSGui.this.clientbpBook.insertBpm(bpm);
      }
   }

   class BrowseHandler implements ActionListener {
      BPMSGui bpmg;

      public BrowseHandler(BPMSGui b) {
         this.bpmg = b;
      }

      public void actionPerformed(ActionEvent e) {
         FileDialog fd = new FileDialog(this.bpmg, "Select Blood Pressure Book File");
         fd.setMode(0);
         fd.pack();
         fd.setLocationRelativeTo((Component)null);
         fd.setVisible(true);
         String s = fd.getFile();
         BPMSGui.this.fNameTf.setText(s);
      }
   }
}
