/*
 * The MIT License
 *
 * @Version 1.1 
 * Copyright 2014 Glenn McGuire <glennmcguire9@gmail.com> <https://github.com/glen-mac>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package my.honeypot;

import static com.esotericsoftware.minlog.Log.error;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main extends javax.swing.JFrame {

    private String PMKey;
    private String Name = null;
    private String[][] User = null;
    private int StockState;
    private double RunningCost = 0.00;
    private SQLInterface sql = new SQLInterface();
    private ArrayList<String> cart = new ArrayList();
    private List Stock = new ArrayList();
    private List PurchaseList = new ArrayList();
    private double Price;
    private int ItemCount = 0;
    private String[] Purchases;
    private String ItemList;
    private Thread Timeout = null;

    public Main(String key) {
        PMKey = key;
        initComponents();
        StartTimeout();
        SQLSetup();
    }

    private void StartTimeout() {
        Timeout = null;
        Timeout = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000 * 30);
                    PurchaseActionPerformed(null);
                    Timeout.join();
                } catch (InterruptedException | NullPointerException e) {
                }
            }
        };
        Timeout.start();
    }

    private void endTimeout() {
        Timeout.interrupt();
        try {
            Timeout.join();
        } catch (InterruptedException ex) {
        }
    }

    private void KillScreen() {
        Timeout = null;
        PMKey = null;
        Name = null;
        User = null;
        StockState = 0;
        RunningCost = 0;
        sql = null;
        cart = null;
        Stock = null;
        PurchaseList = null;
        Price = 0;
        ItemCount = 0;
        Purchases = null;
        ItemList = null;
        System.gc();
    }

    private void SQLSetup() {
        try {
            CartWin.setModel(lm);
            KeyIn.requestFocus();

            User = sql.SQLSend("SELECT Name, Surname, WeekCost, PriorWeekCost, Purchases FROM Users where PMKEYS='" + PMKey + "'", 5);
            Name = (User[0][0]);
            Customer.setText(Name);
            CurrentBillVal.setText("$" + User[2][0]);

            ImageIcon imageIcon = new ImageIcon("faces/" + PMKey + ".jpg");

            PhotoID.setIcon(imageIcon);

            CartUpdate();

        } catch (Exception ex) {
            error(Name + ":" + PMKey, "Exception Caught in SQLSetup");
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        BackPanel = new javax.swing.JPanel();
        CartScrollPane = new javax.swing.JScrollPane();
        CartWin = new javax.swing.JList();
        Remove = new javax.swing.JButton();
        KeyIn = new javax.swing.JTextField();
        Purchase = new javax.swing.JButton();
        InfoPanel = new javax.swing.JPanel();
        Heading = new javax.swing.JLabel();
        Customer = new javax.swing.JLabel();
        SubInfoPanel = new javax.swing.JPanel();
        CurrentBillVal = new javax.swing.JLabel();
        CurrentBill = new javax.swing.JLabel();
        CartCost = new javax.swing.JLabel();
        CartCostVal = new javax.swing.JLabel();
        CartCountVal = new javax.swing.JLabel();
        CartCount = new javax.swing.JLabel();
        PhotoID = new javax.swing.JLabel();
        Help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("14 TOC Purchase Screen (v1.0)");
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        BackPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        BackPanel.setPreferredSize(new java.awt.Dimension(800, 600));

        CartScrollPane.setAutoscrolls(true);

        CartWin.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        CartWin.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "1", "2", "3", "4" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        CartWin.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        CartScrollPane.setViewportView(CartWin);

        Remove.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Remove.setText("Remove Item");
        Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveActionPerformed(evt);
            }
        });

        KeyIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyInActionPerformed(evt);
            }
        });
        KeyIn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                KeyInFocusLost(evt);
            }
        });

        Purchase.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Purchase.setText("Exit Purchase Screen");
        Purchase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PurchaseActionPerformed(evt);
            }
        });

        InfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        Heading.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        Heading.setText(" Welcome");

        Customer.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        Customer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Customer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        SubInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        CurrentBillVal.setText("$0.00");

        CurrentBill.setText("Current Bill");

        CartCost.setText("Cost of Cart");

        CartCostVal.setText("$0.00");

        CartCountVal.setText("0");

        CartCount.setText("Items in Cart");

        javax.swing.GroupLayout SubInfoPanelLayout = new javax.swing.GroupLayout(SubInfoPanel);
        SubInfoPanel.setLayout(SubInfoPanelLayout);
        SubInfoPanelLayout.setHorizontalGroup(
            SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SubInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CartCost)
                    .addComponent(CurrentBill)
                    .addComponent(CartCount))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SubInfoPanelLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CartCostVal)
                            .addComponent(CurrentBillVal)))
                    .addComponent(CartCountVal))
                .addGap(4, 4, 4))
        );
        SubInfoPanelLayout.setVerticalGroup(
            SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SubInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CartCount)
                    .addComponent(CartCountVal))
                .addGap(37, 37, 37)
                .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CartCost)
                    .addComponent(CartCostVal))
                .addGap(37, 37, 37)
                .addGroup(SubInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CurrentBill)
                    .addComponent(CurrentBillVal))
                .addGap(19, 19, 19))
        );

        Help.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Help.setText("Help");
        Help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HelpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout InfoPanelLayout = new javax.swing.GroupLayout(InfoPanel);
        InfoPanel.setLayout(InfoPanelLayout);
        InfoPanelLayout.setHorizontalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, InfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Help, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SubInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Customer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PhotoID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, InfoPanelLayout.createSequentialGroup()
                        .addComponent(Heading)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        InfoPanelLayout.setVerticalGroup(
            InfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InfoPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(Heading)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PhotoID, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(SubInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Help, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout BackPanelLayout = new javax.swing.GroupLayout(BackPanel);
        BackPanel.setLayout(BackPanelLayout);
        BackPanelLayout.setHorizontalGroup(
            BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(InfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(BackPanelLayout.createSequentialGroup()
                        .addComponent(Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(Purchase, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                    .addComponent(KeyIn)
                    .addComponent(CartScrollPane))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        BackPanelLayout.setVerticalGroup(
            BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, BackPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(InfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(BackPanelLayout.createSequentialGroup()
                        .addComponent(CartScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(KeyIn, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(BackPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Remove, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Purchase, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 29;
        gridBagConstraints.ipady = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(7, 6, 7, 6);
        getContentPane().add(BackPanel, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveActionPerformed
        String ItemName = "";
        try {
            String line = CartWin.getSelectedValue().toString();
            ItemName = line.substring(line.indexOf(" ") + 3, line.indexOf('$') - 4);
            cart.add("-" + ItemName);
            CartUpdate();

            if (RunningCost == 0) {
                Purchase.setText("Exit Purchase Screen");
            }
        } catch (Exception ex) {
            error(Name + ":" + PMKey, "Exception Caught in RemoveActionPerformed: '" + ItemName + "'");
        }
    }//GEN-LAST:event_RemoveActionPerformed

    private void KeyInFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_KeyInFocusLost
        KeyIn.requestFocus();
    }//GEN-LAST:event_KeyInFocusLost

    private void KeyInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyInActionPerformed
        try {

            String[][] Prod = sql.SQLSend("SELECT Name, Cost FROM Products where Barcode='" + KeyIn.getText() + "'", 2);
            cart.add(Prod[0][0]);
            CartUpdate();
            CartWin.ensureIndexIsVisible(lm.getSize() - 1);
            if (RunningCost > 0) {
                Purchase.setText("Purchase Cart");
            }
        } catch (Exception ex) {
            error(Name + ":" + PMKey, "Exception Caught in KeyInActionPerformed: '" + KeyIn.getText() + "'");
        } finally {
            KeyIn.setText("");
        }
    }//GEN-LAST:event_KeyInActionPerformed


    private void PurchaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PurchaseActionPerformed
        try {
            if (lm.isEmpty() || (lm.getSize() == 0)) {
                ItemList = "";
                return;
            }

            PurchaseSort(User[4][0]);

            String sqlState = "UPDATE Products SET Stock = CASE Name ";
            String sqlEnd = "";

            for (int i = 0; i < (Stock.size() - 1); i += 2) {
                String name = ((String) Stock.get(i));

                int number = ((int) Stock.get(i + 1));
                StockState = (int) Integer.valueOf(sql.SQLSend("SELECT Stock FROM Products WHERE Name = '" + name + "'", 1)[0][0]);
                if (StockState - number < 0) {
                    throw new NullPointerException(name);
                }
                sqlState += ("WHEN '" + name + "' THEN (Stock - " + number + ") ");
                sqlEnd += "'" + name + "', ";
                PurchaseAdd(name, number);
            }

            sqlState += "END WHERE Name IN (" + sqlEnd.substring(0, sqlEnd.length() - 2) + ");";

            sql.SQLUpdate(sqlState);

            sql.SQLUpdate("UPDATE Users SET WeekCost = (WeekCost + " + RunningCost + ") WHERE PMKEYS = '" + PMKey + "'");

            PurchaseSend();

            sql.SQLUpdate("UPDATE Users SET Purchases = '" + ItemList.substring(5) + "' WHERE PMKEYS = '" + PMKey + "'");

        } catch (NullPointerException ex) {
            error(Name + ":" + PMKey, "Exception Caught in PurchaseActionPerformed - Negative Stock Purchase");
            JOptionPane.showMessageDialog(null, "The stock of " + ex.getMessage() + " is not great enough to allow this purchase.", "ERROR", JOptionPane.ERROR_MESSAGE);
            //  } catch (Exception ex) {
            // error(Name + ":" + PMKey, "Exception Caught in PurchaseActionPerformed");
            // JOptionPane.showMessageDialog(null, "There was an error with the purchase - Purchase not successfull.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            endTimeout();
            KillScreen();
            Login l = new Login();
            l.setVisible(true);
            l.pack();
            dispose();
        }

    }//GEN-LAST:event_PurchaseActionPerformed

    private void HelpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HelpActionPerformed
        JOptionPane.showMessageDialog(null, "Scan the barcode of the item you wish to purchase. \n\nIf an item does not have an individual barcode on its packaging, scan the appropriate barcode\n listed on the wall. \n\nScan an item multiple times to make multiple purchases of the same item. \n\nPress the purchase button to finalise your purchase of the listed items above.\n\nTo remove an item, select the item on the screen and press the 'REMOVE ITEM' button. \n\nTo exit this screen without making any purchases, remove all items from the screens list and press \nthe 'PURCHASE' button. \n \nIf you experience any difficulties using this program, please contact OFFCDT Harding - 0413 789 121.", "HELP", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_HelpActionPerformed

    private void CartUpdate() {
        lm.removeAllElements();
        Stock.clear();
        RunningCost = 0;

        for (String item : cart) {
            int index = Stock.indexOf(item);
            if (index != -1) {
                Stock.set(index + 1, 1 + ((int) Stock.get(index + 1)));
            } else if (item.startsWith("-")) {
                index = Stock.indexOf(item.substring(1));
                if (-1 + ((int) Stock.get(index + 1)) > -1) {
                    Stock.set(index + 1, ((int) Stock.get(index + 1)) - 1);
                }
            } else {
                Stock.add(item);
                Stock.add(1);
            }
        }

        for (int i = 0; i < (Stock.size() - 1); i += 2) {
            if ((int) Stock.get(i + 1) > 0) {
                Price = Double.valueOf(sql.SQLSend("SELECT Cost FROM Products WHERE Name = '" + Stock.get(i) + "'", 1)[0][0]);
                double Lotcost = (double) (Double.valueOf(Stock.get(i + 1).toString()) * Price);
                lm.addElement(Stock.get(i + 1) + " x " + Stock.get(i) + "  = $" + (String.format("%.2f", Lotcost)));
                ItemCount += (int) Stock.get(i + 1);
                RunningCost += Lotcost;
            }
        }

        CartCountVal.setText("" + ItemCount);
        CartCostVal.setText("$" + String.format("%.2f", RunningCost));

        Price = 0;
        ItemCount = 0;

        endTimeout();

        StartTimeout();
    }

    private void PurchaseSort(String list) {
        if (list.length() > 0) {
            Purchases = list.split(",");
            for (String item : Purchases) {
                String[] temp = item.split(":");
                PurchaseList.add(temp[0]);
                PurchaseList.add(temp[1]);
            }
        }
    }

    private void PurchaseAdd(String name, int lots) {
        for (int i = 0; i < PurchaseList.size() - 1; i += 2) {
            int index = PurchaseList.indexOf(name);
            if (index != -1) {
                int tNum = Integer.parseInt(PurchaseList.get(index + 1).toString()) + lots;
                PurchaseList.set(index + 1, tNum);
                return;
            }
        }
        PurchaseList.add(name);
        PurchaseList.add(lots);
    }

    private void PurchaseSend() {
        for (int i = 0; i < PurchaseList.size() - 1; i += 2) {
            if (Integer.valueOf(PurchaseList.get(i + 1).toString()) != 0) {
                ItemList += ("," + PurchaseList.get(i) + ":" + PurchaseList.get(i + 1));
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (!"Nimbus".equals(info.getName())) {
                } else {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    private final DefaultListModel lm = new DefaultListModel();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BackPanel;
    private javax.swing.JLabel CartCost;
    private javax.swing.JLabel CartCostVal;
    private javax.swing.JLabel CartCount;
    private javax.swing.JLabel CartCountVal;
    private javax.swing.JScrollPane CartScrollPane;
    private javax.swing.JList CartWin;
    private javax.swing.JLabel CurrentBill;
    private javax.swing.JLabel CurrentBillVal;
    private javax.swing.JLabel Customer;
    private javax.swing.JLabel Heading;
    private javax.swing.JButton Help;
    private javax.swing.JPanel InfoPanel;
    private javax.swing.JTextField KeyIn;
    private javax.swing.JLabel PhotoID;
    private javax.swing.JButton Purchase;
    private javax.swing.JButton Remove;
    private javax.swing.JPanel SubInfoPanel;
    // End of variables declaration//GEN-END:variables
}
