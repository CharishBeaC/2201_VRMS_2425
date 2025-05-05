package Main;/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import database_connector.DBKonek;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.formdev.flatlaf.FlatLightLaf;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.border.TitledBorder;
import com.itextpdf.text.*;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;

import java.io.File;
import java.io.FileOutputStream;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author User
 */
public class Staff extends javax.swing.JFrame {
    private Connection kon;
    private JTable machineTable; // Add a field to store the machine table reference

    /**
     *
     */
    public Staff() {
        // Apply FlatLaf theme before initializing components
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.themes.FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        initComponents();
        DBKonek dbc = new DBKonek();
        kon = dbc.getConnection();
        
        // Set modern fonts for better appearance
        setFonts();
        
        // Make the booking panel scrollable and add the machine table
        setupScrollableBookingPanel();
        
        // Initialize machine table data
        loadMachineTableData();
    }

    private void setFonts() {
        // Set consistent, modern fonts
        java.awt.Font labelFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13);
        java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13);
        java.awt.Font headerFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
        
        // Apply fonts to components
        cusname.setFont(labelFont);
        connum.setFont(labelFont);
        email.setFont(labelFont);
        adds.setFont(labelFont);
        jLabel20.setFont(boldFont);
        jLabel21.setFont(boldFont);
        jLabel22.setFont(boldFont);
        jLabel23.setFont(boldFont);
        
        // Style the rent button for better visibility
        rent.setFont(headerFont);
        rent.setBackground(new Color(76, 175, 80));
        rent.setForeground(Color.WHITE);
        
        // Make the clear label more visible
        jLabel24.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        jLabel24.setForeground(new Color(33, 150, 243));
        
        // Style the info text area
        info.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    }

    private void clearField(){
        cusname.setText("");
        connum.setText("");
        email.setText("");
        adds.setText("");
        info.setText("");
        
        // Reset borders on machine buttons
        machine1bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine2bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine3bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine4bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine5bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine6bttn1.setBorder(new javax.swing.border.MatteBorder(null));
    }

    private void setupScrollableBookingPanel() {
        // Create a scroll pane for the entire content
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Create table for machines at the top with modern styling
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 212), 1, true),
                "Available Machines",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(0, 120, 212)
            )
        ));
        tablePanel.setBackground(new Color(240, 248, 255));
        
        // Create the table model with proper column names matching your database
        DefaultTableModel tableModel = new DefaultTableModel(
            new Object[][] {}, 
            new String[] {"Machine ID", "Machine Name", "Status", "Price", "Remarks"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Non-editable table
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                if (columnIndex == 3) return Double.class;
                return String.class;
            }
        };
        machineTable = new JTable(tableModel);
        machineTable.setRowHeight(28);
        machineTable.setShowGrid(true);
        machineTable.setGridColor(new Color(230, 230, 230));
        machineTable.setGridColor(new Color(230, 230, 230));
        machineTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        machineTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        machineTable.getTableHeader().setBackground(new Color(240, 240, 240));
        machineTable.setSelectionBackground(new Color(232, 242, 254));
        machineTable.setSelectionForeground(Color.BLACK);
        
        // Set column widths
        machineTable.getColumnModel().getColumn(0).setPreferredWidth(80);  // ID
        machineTable.getColumnModel().getColumn(1).setPreferredWidth(200); // Name
        machineTable.getColumnModel().getColumn(2).setPreferredWidth(100); // Status
        machineTable.getColumnModel().getColumn(3).setPreferredWidth(100); // Price
        machineTable.getColumnModel().getColumn(4).setPreferredWidth(300); // Remarks
        
        // Add selection listener to show details when clicking on a machine
        machineTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && machineTable.getSelectedRow() != -1) {
                int machineId = (int) machineTable.getValueAt(machineTable.getSelectedRow(), 0);
                loadMachineDetails(machineId);
            }
        });
        
        JScrollPane tableScrollPane = new JScrollPane(machineTable);
        tableScrollPane.setPreferredSize(new Dimension(700, 180));
        tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Add refresh button with better styling
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        refreshButton.setBackground(new Color(240, 240, 240));
        refreshButton.setFocusPainted(false);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        buttonPanel.add(refreshButton);
        tablePanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Set action for refresh button
        refreshButton.addActionListener(e -> loadMachineTableData());
        
        // Create a main panel to hold everything with better styling
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 248, 255));
        
        // Add the table panel at the top
        mainPanel.add(tablePanel);
        
        // Add some spacing with a separator for visual division
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        separator.setForeground(new Color(200, 200, 200));
        mainPanel.add(separator);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        
        // Create a styled panel for the booking section
        JPanel bookingPanel = new JPanel(new BorderLayout(10, 10));
        bookingPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 10, 10, 10),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 1, true),
                "Rent a Machine",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(76, 175, 80)
            )
        ));
        bookingPanel.setBackground(new Color(240, 248, 255));
        bookingPanel.add(jPanel15, BorderLayout.CENTER);
        
        // Add the original content panel (jPanel15) wrapped in our styled container
        jPanel15.setBackground(new Color(240, 248, 255));
        mainPanel.add(bookingPanel);
        
        // Set the view of the scroll pane to our main panel
        scrollPane.setViewportView(mainPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smoother scrolling
        
        // Clear jPanel10 and add the scroll pane
        jPanel10.removeAll();
        jPanel10.setLayout(new BorderLayout());
        jPanel10.add(scrollPane, BorderLayout.CENTER);
        jPanel10.revalidate();
        jPanel10.repaint();
    }
    /**
     * Returns the machine table reference
     * @return JTable containing machine data
     */
    private JTable findMachineTable() {
        return machineTable;
    }

    private void loadMachineTableData() {
        Connection connection = DBKonek.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Find the machine table
            JTable machineTable = findMachineTable();
            if (machineTable == null) return;
            if (machineTable == null) return;
            
            DefaultTableModel model = (DefaultTableModel) machineTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            String query = "SELECT machine_id, machine_name, status, price, remarks FROM videoke_machines ORDER BY machine_id";
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("machine_id"),
                    rs.getString("machine_name"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getString("remarks")
                });
            }
            
            // Apply cell coloring based on status with better colors
            machineTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    
                    if (isSelected) {
                        c.setBackground(new Color(232, 242, 254));
                        c.setForeground(Color.BLACK);
                        return c;
                    }
                    
                    String status = (String) table.getModel().getValueAt(row, 2);
                    switch (status) {
                        case "Available":
                            c.setBackground(new Color(237, 247, 237));
                            break;
                        case "Rented":
                            c.setBackground(new Color(253, 237, 237));
                            break;
                        case "Reserved":
                            c.setBackground(new Color(255, 248, 225));
                            break;
                        default:
                            c.setBackground(new Color(240, 240, 240));
                            break;
                    }
                    c.setForeground(Color.BLACK);
                    
                    // Format the price column to show as currency
                    if (column == 3 && value != null) {
                        double price = (Double) value;
                        setText(String.format("₱%.2f", price));
                    }
                    
                    return c;
                }
            });
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading machine data: " + e.getMessage(), 
                                         "Database Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadMachineDetails(int machineId) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            // Updated SQL to include the inclusion column
            String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
            pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, machineId);
            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                String machineName = rs.getString("machine_name");
                String status = rs.getString("status");
                String remarks = rs.getString("remarks");
                double price = rs.getDouble("price");
                String inclusions = rs.getString("inclusion"); // Get inclusions from DB
                
                // Determine package type based on machine ID
                String packageType = "Standard";
                switch (machineId) {
                    case 1:
                    case 6:
                        packageType = "Basic Package";
                        break;
                    case 2:
                        packageType = "Party Package";
                        break;
                    case 3:
                    case 4:
                    case 5:
                        packageType = "Premium Package";
                        break;
                }
                
                // If inclusions from DB is empty/null, use default text
                if (inclusions == null || inclusions.trim().isEmpty()) {
                    inclusions = "   -  Standard videoke package\n   -  Contact for details";
                }
                
                String machineInfo = "                                  " + packageType + "\n"
                                    + "--------------------------------------------------------------------------\n"
                                    + "Machine Name: " + machineName + "\n"
                                    + "Status: " + status + "\n"
                                    + "Remarks: " + remarks + "\n"
                                    + "Inclusions:\n"
                                    + inclusions + "\n"
                                    + "   -  Per Day Rental\n"
                                    + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                                    + " Note: Any damage or loss during the rental period will be the client's responsibility.";
                
                info.setText(machineInfo);
                
                // Highlight the corresponding machine button for visual feedback
                resetButtonBorders();
                switch (machineId) {
                    case 1: machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                    case 2: machine2bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                    case 3: machine3bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                    case 4: machine4bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                    case 5: machine5bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                    case 6: machine6bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3)); break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Helper method to reset button borders
    private void resetButtonBorders() {
        machine1bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine2bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine3bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine4bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine5bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine6bttn1.setBorder(new javax.swing.border.MatteBorder(null));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dashboard = new javax.swing.JButton();
        customermngmnt = new javax.swing.JButton();
        usagemonitor = new javax.swing.JButton();
        mngequipment = new javax.swing.JButton();
        mngreservation = new javax.swing.JButton();
        logoutstaff = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        machine1bttn1 = new javax.swing.JButton();
        machine2bttn1 = new javax.swing.JButton();
        machine3bttn1 = new javax.swing.JButton();
        machine4bttn1 = new javax.swing.JButton();
        machine5bttn1 = new javax.swing.JButton();
        machine6bttn1 = new javax.swing.JButton();
        info = new java.awt.TextArea();
        rent = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cusname = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        connum = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        adds = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        booking = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 600));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconAdmin.png"))); // NOI18N
        jLabel2.setText("STAFF");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 20, -1, -1));

        dashboard.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        dashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dashicon.png"))); // NOI18N
        dashboard.setText("Dashboard");
        dashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dashboardActionPerformed(evt);
            }
        });
        jPanel1.add(dashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 210, 40));

        customermngmnt.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        customermngmnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/customermngmnt.png"))); // NOI18N
        customermngmnt.setText("Customer Management");
        customermngmnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customermngmntActionPerformed(evt);
            }
        });
        jPanel1.add(customermngmnt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 210, 40));

        usagemonitor.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        usagemonitor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usagemonitor.png"))); // NOI18N
        usagemonitor.setText("Usage Monitoring");
        usagemonitor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usagemonitorActionPerformed(evt);
            }
        });
        jPanel1.add(usagemonitor, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 210, 40));

        mngequipment.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        mngequipment.setIcon(new javax.swing.ImageIcon(getClass().getResource("/equipmngmnt.png"))); // NOI18N
        mngequipment.setText("Manage Equipment");
        mngequipment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngequipmentActionPerformed(evt);
            }
        });
        jPanel1.add(mngequipment, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 210, 40));

        mngreservation.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        mngreservation.setIcon(new javax.swing.ImageIcon(getClass().getResource("/reservationIcon.png"))); // NOI18N
        mngreservation.setText("Manage Reservation");
        mngreservation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mngreservationActionPerformed(evt);
            }
        });
        jPanel1.add(mngreservation, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 210, 40));

        logoutstaff.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        logoutstaff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logouticon.png"))); // NOI18N
        logoutstaff.setText("Log out");
        logoutstaff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutstaffActionPerformed(evt);
            }
        });
        jPanel1.add(logoutstaff, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 510, 120, 30));

        jTabbedPane1.setBackground(new java.awt.Color(204, 255, 204));

        jPanel9.setBackground(new java.awt.Color(204, 255, 255));
        jPanel9.setPreferredSize(new java.awt.Dimension(740, 490));
        jPanel9.setRequestFocusEnabled(false);
        jPanel9.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dashboard", jPanel9);

        jPanel10.setBackground(new java.awt.Color(204, 255, 255));

        jPanel15.setBackground(new java.awt.Color(204, 255, 255));
        jPanel15.setPreferredSize(new java.awt.Dimension(740, 490));
        jPanel15.setRequestFocusEnabled(false);
        jPanel15.setVerifyInputWhenFocusTarget(false);

        machine1bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine1bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine1.png"))); // NOI18N
        machine1bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine1bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine1bttn1ActionPerformed(evt);
            }
        });

        machine2bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine2bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine2.png"))); // NOI18N
        machine2bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine2bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine2bttn1ActionPerformed(evt);
            }
        });

        machine3bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine3bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine3.png"))); // NOI18N
        machine3bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine3bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine3bttn1ActionPerformed(evt);
            }
        });

        machine4bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine4bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine4.png"))); // NOI18N
        machine4bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine4bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine4bttn1ActionPerformed(evt);
            }
        });

        machine5bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine5bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine5.png"))); // NOI18N
        machine5bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine5bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine5bttn1ActionPerformed(evt);
            }
        });

        machine6bttn1.setBackground(new java.awt.Color(102, 204, 255));
        machine6bttn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/machine6.png"))); // NOI18N
        machine6bttn1.setBorder(new javax.swing.border.MatteBorder(null));
        machine6bttn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                machine6bttn1ActionPerformed(evt);
            }
        });

        info.setBackground(new java.awt.Color(220, 230, 250));
        info.setEditable(false);
        info.setForeground(new java.awt.Color(0, 0, 100));

        rent.setBackground(new java.awt.Color(0, 204, 0));
        rent.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        rent.setText("RENT");
        rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel14.setText("Machine 1");

        jLabel15.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel15.setText("Machine 2");

        jLabel16.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel16.setText("Machine 6");

        jLabel17.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel17.setText("Machine 3");

        jLabel18.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel18.setText("Machine 4");

        jLabel19.setFont(new java.awt.Font("Monospaced", 1, 12)); // NOI18N
        jLabel19.setText("Machine 5");

        jLabel20.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel20.setText("Customer Name:");

        jLabel21.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel21.setText("Contact Number:");

        cusname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cusnameActionPerformed(evt);
            }
        });

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel22.setText("Email:");

        connum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connumActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel23.setText("Address:");

        adds.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addsActionPerformed(evt);
            }
        });

        jLabel24.setText("CLEAR");
        jLabel24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel24MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel19)
                .addGap(97, 97, 97)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(machine5bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(machine6bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(rent, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel24))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(machine1bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(machine3bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel17))))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(machine2bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(machine4bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel18))))
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel15Layout.createSequentialGroup()
                                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(0, 19, Short.MAX_VALUE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(cusname)
                                            .addComponent(adds, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                            .addComponent(email)))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(connum, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(adds, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rent, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24))
                        .addGap(60, 60, 60))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(machine1bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14)
                                .addGap(3, 3, 3)
                                .addComponent(machine3bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel17)
                                .addGap(3, 3, 3)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(machine5bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(machine6bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(4, 4, 4)
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel16)))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(machine2bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel15)
                                        .addGap(3, 3, 3)
                                        .addComponent(machine4bttn1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel15Layout.createSequentialGroup()
                                        .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cusname, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(connum, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(4, 4, 4)
                                .addComponent(jLabel18)))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel10Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Manage Booking", jPanel10);

        jPanel11.setBackground(new java.awt.Color(204, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Customer Management", jPanel11);

        jPanel12.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Usage Monitoring", jPanel12);

        jPanel13.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Manage Equipment", jPanel13);

        jPanel14.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Manage Reservation", jPanel14);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 740, 530));

        booking.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        booking.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bookingggicon.png"))); // NOI18N
        booking.setText("Manage Booking ");
        booking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookingActionPerformed(evt);
            }
        });
        jPanel1.add(booking, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, 210, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiadmindashh.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dashboardActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_dashboardActionPerformed

    private void customermngmntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customermngmntActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_customermngmntActionPerformed

    private void mngequipmentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngequipmentActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_mngequipmentActionPerformed

    private void usagemonitorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usagemonitorActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_usagemonitorActionPerformed

    private void mngreservationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mngreservationActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(5);
    }//GEN-LAST:event_mngreservationActionPerformed

    private void logoutstaffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutstaffActionPerformed
        // TODO add your handling code here:
        int choice = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to logout?",
        "Confirm Logout",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.QUESTION_MESSAGE
    );

    if (choice == JOptionPane.YES_OPTION) {
        Login loginFrame = new Login();  
        loginFrame.setVisible(true);    
        this.dispose();
   }
    }//GEN-LAST:event_logoutstaffActionPerformed

    private void bookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookingActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_bookingActionPerformed

    private void machine1bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine1bttn1ActionPerformed
        // TODO add your handling code here
        // Connect to the database
    // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 1); // Fetch data for machine_id = 1

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Platinum Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  32-inch TV\n"
                          + "   -  Updated Songs, Quality Sound";
            }

            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 1.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_machine1bttn1ActionPerformed

    private void machine2bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine2bttn1ActionPerformed
        // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 2); // Fetch data for machine_id = 1

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Platinum Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  32-inch TV\n"
                          + "   -  Updated Songs, Quality Sound";
            }

            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 1.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_machine2bttn1ActionPerformed

    private void machine3bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine3bttn1ActionPerformed
        // TODO add your handling code here:
       // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 3); // Fetch data for machine_id = 1

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Platinum Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  32-inch TV\n"
                          + "   -  Updated Songs, Quality Sound";
            }

            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 1.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_machine3bttn1ActionPerformed

    private void machine4bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine4bttn1ActionPerformed
        // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 4); // Fetch data for machine_id = 1

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Platinum Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  32-inch TV\n"
                          + "   -  Updated Songs, Quality Sound";
            }

            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 1.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_machine4bttn1ActionPerformed

    private void machine5bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine5bttn1ActionPerformed
        // TODO add your handling code here
         // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 5); // Fetch data for machine_id = 1

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Platinum Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  32-inch TV\n"
                          + "   -  Updated Songs, Quality Sound";
            }

            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine1bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 1.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }//GEN-LAST:event_machine5bttn1ActionPerformed

    private void machine6bttn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_machine6bttn1ActionPerformed
        // TODO add your handling code here:
           // TODO add your handling code here
    Connection connection = DBKonek.getConnection();

    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.");
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    try {
        // Updated SQL query to include the inclusion column
        String sql = "SELECT machine_name, status, remarks, price, inclusion FROM videoke_machines WHERE machine_id = ?";
        pstmt = connection.prepareStatement(sql);
        pstmt.setInt(1, 6); // Fetch data for machine_id = 6

        rs = pstmt.executeQuery();

        if (rs.next()) {
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            String remarks = rs.getString("remarks");
            double price = rs.getDouble("price");
            String inclusions = rs.getString("inclusion"); // Get from database

            // If inclusions is empty/null, use a default text
            if (inclusions == null || inclusions.trim().isEmpty()) {
                inclusions = "   -  1 Videoke Unit (Basic Player)\n"
                          + "   -  2 Wired Microphones\n"
                          + "   -  Updated Songs, Quality Sound";
            }
            
            // Updated info with daily rate and paraphrased note
            String machineInfo = "                                  Basic Package\n"
                               + "--------------------------------------------------------------------------\n"
                               + "Machine Name: " + machineName + "\n"
                               + "Status: " + status + "\n"
                               + "Remarks: " + remarks + "\n"
                               + "Inclusions:\n"
                               + inclusions + "\n"
                               + "   -  Per Day Rental\n"
                               + " Price: ₱" + String.format("%.2f", price) + " / day\n"
                               + " Reminder: Any damage or loss during the rental period will be the client's responsibility.";

            info.setText(machineInfo);
            
            // Highlight selected button
            resetButtonBorders();
            machine6bttn1.setBorder(BorderFactory.createLineBorder(new Color(0, 120, 215), 3));
            
        } else {
            info.setText("No data found for Machine ID 6.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
        info.setText("Error retrieving machine data.");
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        }

        
    //GEN-LAST:event_machine6bttn1ActionPerformed

    private void rentActionPerformed(java.awt.event.ActionEvent evt) {
    try {
        UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());

        // Get customer information
        String customerName = cusname.getText().trim();
        String contactNumber = connum.getText().trim();
        String emailAddress = email.getText().trim();
        String address = adds.getText().trim();
        String machineInfoText = info.getText().trim();

        // Validate input fields
        if (customerName.isEmpty() || contactNumber.isEmpty() || emailAddress.isEmpty()
                || address.isEmpty() || machineInfoText.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Extract machine name from info text
        final StringBuilder machineNameBuilder = new StringBuilder();
        for (String line : machineInfoText.split("\n")) {
            if (line.startsWith("Machine Name: ")) {
                machineNameBuilder.append(line.substring("Machine Name: ".length()).trim());
                break;
            }
        }
        final String machineName = machineNameBuilder.toString();

        if (machineName.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid machine info! Please select a machine.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Format customer details for confirmation
        String customerDetails = "Customer Name: " + customerName + "\n"
                + "Contact Number: " + contactNumber + "\n"
                + "Email: " + emailAddress + "\n"
                + "Address: " + address + "\n"
                + "Machine: " + machineName;

        int confirm = JOptionPane.showConfirmDialog(null, customerDetails, "Confirm Rental", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        // Fetch machine details
        int machineId = 0;
        double machinePrice = 0;
        String status = "";

        PreparedStatement fetchStmt = kon.prepareStatement(
                "SELECT machine_id, price, status FROM videoke_machines WHERE machine_name = ?");
        fetchStmt.setString(1, machineName);
        ResultSet rs = fetchStmt.executeQuery();
        
        if (rs.next()) {
            machineId = rs.getInt("machine_id");
            machinePrice = rs.getDouble("price");
            status = rs.getString("status");
        } else {
            JOptionPane.showMessageDialog(null, "Machine not found in database!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String additionalDetails = "";
        double additionalCost = 0;

        // Ask for extras
        JCheckBox speakerCheck = new JCheckBox("Speaker (+₱100 each)");
        JCheckBox lightCheck = new JCheckBox("Disco Light (+₱60 each)");
        JTextField speakerQty = new JTextField("1", 5);
        JTextField lightQty = new JTextField("1", 5);

        JPanel extrasPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        extrasPanel.add(speakerCheck);
        extrasPanel.add(speakerQty);
        extrasPanel.add(lightCheck);
        extrasPanel.add(lightQty);

        int extrasConfirm = JOptionPane.showConfirmDialog(null, extrasPanel, "Add Extras", JOptionPane.OK_CANCEL_OPTION);
        if (extrasConfirm == JOptionPane.OK_OPTION) {
            try {
                if (speakerCheck.isSelected()) {
                    int qty = Integer.parseInt(speakerQty.getText().trim());
                    if (qty < 0) throw new NumberFormatException();
                    additionalCost += qty * 100;
                    additionalDetails += "Speaker x" + qty + " = ₱" + (qty * 100) + "\n";
                }
                if (lightCheck.isSelected()) {
                    int qty = Integer.parseInt(lightQty.getText().trim());
                    if (qty < 0) throw new NumberFormatException();
                    additionalCost += qty * 60;
                    additionalDetails += "Disco Light x" + qty + " = ₱" + (qty * 60) + "\n";
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Please enter valid quantities!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        // Ask for rental dates
        JDateChooser rentalStartChooser = new JDateChooser();
        JDateChooser rentalEndChooser = new JDateChooser();
        rentalStartChooser.setDateFormatString("yyyy-MM-dd");
        rentalEndChooser.setDateFormatString("yyyy-MM-dd");
        rentalStartChooser.setDate(new java.util.Date()); // Default to today

        JPanel rentalPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        rentalPanel.add(new JLabel("Rental Start Date:"));
        rentalPanel.add(rentalStartChooser);
        rentalPanel.add(new JLabel("Return Date:"));
        rentalPanel.add(rentalEndChooser);

        int rentalConfirm = JOptionPane.showConfirmDialog(null, rentalPanel, "Select Rental Dates", JOptionPane.OK_CANCEL_OPTION);
        if (rentalConfirm != JOptionPane.OK_OPTION) return;
        
        java.util.Date rentalStartDate = rentalStartChooser.getDate();
        java.util.Date rentalEndDate = rentalEndChooser.getDate();

        if (rentalStartDate == null || rentalEndDate == null || rentalEndDate.before(rentalStartDate)) {
            JOptionPane.showMessageDialog(null, "Invalid rental dates!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Calculate total days and cost
        long diffInMillies = rentalEndDate.getTime() - rentalStartDate.getTime();
        int days = (int) (diffInMillies / (1000 * 60 * 60 * 24)) + 1;
        double totalCost = (machinePrice * days) + additionalCost;
        
        // Show rental summary
        String rentalSummary = "Machine: " + machineName + "\n" +
                            "Rental Period: " + days + " days\n" +
                            "Machine Cost: ₱" + machinePrice + " x " + days + " days = ₱" + (machinePrice * days) + "\n";
        
        if (!additionalDetails.isEmpty()) {
            rentalSummary += "Additional Items:\n" + additionalDetails;
        }
        
        rentalSummary += "Total Cost: ₱" + totalCost;
        
        int finalConfirm = JOptionPane.showConfirmDialog(null, rentalSummary, 
                "Confirm Rental Details", JOptionPane.YES_NO_OPTION);
        if (finalConfirm != JOptionPane.YES_OPTION) return;

        // ASK FOR PAYMENT AMOUNT HERE
        JPanel paymentPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        paymentPanel.add(new JLabel("Total Amount:"));
        JTextField totalField = new JTextField(String.format("₱%.2f", totalCost));
        totalField.setEditable(false);
        paymentPanel.add(totalField);
        
        paymentPanel.add(new JLabel("Amount Paid:"));
        JTextField paidField = new JTextField();
        paymentPanel.add(paidField);
        
        paymentPanel.add(new JLabel("Payment Type:"));
        JComboBox<String> paymentTypeCombo = new JComboBox<>(new String[]{"Cash", "Card", "GCash", "PayMaya"});
        paymentPanel.add(paymentTypeCombo);
        
        paymentPanel.add(new JLabel("Change:"));
        JTextField changeField = new JTextField();
        changeField.setEditable(false);
        paymentPanel.add(changeField);
        
        // Add document listener to calculate change
        paidField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) { updateChange(); }
            public void removeUpdate(DocumentEvent e) { updateChange(); }
            public void changedUpdate(DocumentEvent e) { updateChange(); }
            
            private void updateChange() {
                try {
                    double paid = Double.parseDouble(paidField.getText().trim());
                    double change = paid - totalCost;
                    changeField.setText(String.format("₱%.2f", change));
                } catch (NumberFormatException ex) {
                    changeField.setText("Invalid amount");
                }
            }
        });
        
        int paymentConfirm = JOptionPane.showConfirmDialog(null, paymentPanel, "Payment", JOptionPane.OK_CANCEL_OPTION);
        if (paymentConfirm != JOptionPane.OK_OPTION) return;
        
        // Validate payment
        double amountPaid;
        try {
            amountPaid = Double.parseDouble(paidField.getText().trim());
            if (amountPaid < totalCost) {
                JOptionPane.showMessageDialog(null, "Payment amount must be at least the total cost!", "Invalid Payment", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid payment amount!", "Invalid Payment", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Convert to SQL dates
        java.sql.Date sqlStartDate = new java.sql.Date(rentalStartDate.getTime());
        java.sql.Date sqlEndDate = new java.sql.Date(rentalEndDate.getTime());
        
        // Insert into rental_logs and get the generated ID
        PreparedStatement rentalStmt = kon.prepareStatement(
                "INSERT INTO rental_logs (customer_name, contact_number, email, address, " +
                "machine_id, machine_name, additional_item, rental_start_date, rental_end_date, Status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pickup')", Statement.RETURN_GENERATED_KEYS);

        rentalStmt.setString(1, customerName);
        rentalStmt.setString(2, contactNumber);
        rentalStmt.setString(3, emailAddress);
        rentalStmt.setString(4, address);
        rentalStmt.setInt(5, machineId);
        rentalStmt.setString(6, machineName);
        rentalStmt.setString(7, additionalDetails);
        rentalStmt.setDate(8, sqlStartDate);
        rentalStmt.setDate(9, sqlEndDate);
        rentalStmt.executeUpdate();

        // Get the generated rental_log_id
        int logId = 0;
        ResultSet rentalKeys = rentalStmt.getGeneratedKeys();
        if (rentalKeys.next()) {
            logId = rentalKeys.getInt(1);
        } else {
            throw new SQLException("Creating rental log failed, no ID obtained.");
        }

        // Update machine status to "Reserved"
        PreparedStatement updateStmt = kon.prepareStatement(
                "UPDATE videoke_machines SET status = 'Reserved' WHERE machine_id = ?");
        updateStmt.setInt(1, machineId);
        updateStmt.executeUpdate();

        // Insert into payments with log_id (not reservation_id)
        String paymentType = (String) paymentTypeCombo.getSelectedItem();
        PreparedStatement paymentStmt = kon.prepareStatement(
                "INSERT INTO payments (log_id, amount_paid, payment_type) VALUES (?, ?, ?)");
        paymentStmt.setInt(1, logId);  // Use the log_id from rental_logs
        paymentStmt.setDouble(2, amountPaid);
        paymentStmt.setString(3, paymentType);
        paymentStmt.executeUpdate();
        
        // Generate receipt
        double change = amountPaid - totalCost;
        StringBuilder receiptBuilder = new StringBuilder();
        receiptBuilder.append("\n====== VIDEOKE RENTAL RECEIPT ======\n")
                     .append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())).append("\n")
                     .append("Customer: ").append(customerName).append("\n")
                     .append("Contact: ").append(contactNumber).append("\n\n")
                     .append("Machine: ").append(machineName).append("\n")
                     .append("Rental Period: ").append(new SimpleDateFormat("yyyy-MM-dd").format(rentalStartDate))
                     .append(" to ").append(new SimpleDateFormat("yyyy-MM-dd").format(rentalEndDate)).append("\n")
                     .append("Duration: ").append(days).append(" days\n\n")
                     .append("Status: Pickup\n\n"); // Changed to "Pickup" for better clarity
                       
        if (!additionalDetails.isEmpty()) {
            receiptBuilder.append("Additional Items:\n").append(additionalDetails).append("\n");
        }
        
        receiptBuilder.append("---------------------------------\n")
                      .append("Subtotal:      ₱").append(machinePrice * days).append("\n")
                      .append("Additional:    ₱").append(additionalCost).append("\n")
                      .append("TOTAL:         ₱").append(totalCost).append("\n")
                      .append("---------------------------------\n")
                      .append("Payment Type:   ").append(paymentType).append("\n")
                      .append("Amount Paid:   ₱").append(amountPaid).append("\n")
                      .append("Change:        ₱").append(change).append("\n")
                      .append("=================================\n")
                      .append("    Thank you for your rental!   \n")
                      .append("  Please return on or before:    \n")
                      .append("  ").append(new SimpleDateFormat("yyyy-MM-dd").format(rentalEndDate)).append("\n")
                      .append("=================================");
        
        final String receipt = receiptBuilder.toString();
        
        // Create receipt text area
        JTextArea receiptArea = new JTextArea(receipt);
        receiptArea.setEditable(false);
        receiptArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(receiptArea);
        scrollPane.setPreferredSize(new Dimension(350, 400));
        
        // Create a panel with the receipt and a "Print to PDF" button
        JPanel receiptPanel = new JPanel(new BorderLayout(0, 10));
        receiptPanel.add(scrollPane, BorderLayout.CENTER);
        
        JButton printButton = new JButton("Save as PDF");
        printButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        printButton.setBackground(new Color(66, 139, 202));
        printButton.setForeground(Color.WHITE);
        
        // Create a panel for the button with some padding
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(printButton);
        receiptPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Create a non-modal dialog to display the receipt with PDF button
        final JDialog receiptDialog = new JDialog(this, "Rental Receipt", false);
        receiptDialog.setLayout(new BorderLayout());
        receiptDialog.add(receiptPanel);
        receiptDialog.pack();
        receiptDialog.setLocationRelativeTo(this);
        
        // Handle the PDF generation when the button is clicked
        printButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save PDF Receipt");
            fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
            fileChooser.setSelectedFile(new File("Rental_Receipt_" + customerName.replaceAll("\\s", "_") + ".pdf"));
            
            if (fileChooser.showSaveDialog(receiptDialog) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                // Add .pdf extension if not present
                if (!file.getName().toLowerCase().endsWith(".pdf")) {
                    file = new File(file.getAbsolutePath() + ".pdf");
                }
                
                try {
                    generatePdfReceipt(receipt, file, customerName, machineName, sqlStartDate, sqlEndDate, paymentType);
                    JOptionPane.showMessageDialog(
                        receiptDialog,
                        "Receipt saved as PDF successfully!",
                        "PDF Created",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(
                        receiptDialog,
                        "Error saving PDF: " + ex.getMessage(),
                        "PDF Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    ex.printStackTrace();
                }
            }
        });
        
        receiptDialog.setVisible(true);
        
        // Refresh the machine table to show updated status
        loadMachineTableData();
        
        // Clear form fields
        clearField();
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Exception", JOptionPane.ERROR_MESSAGE);
    }
}

/**
 * Generate a PDF receipt file using iText library
 * 
 * @param receiptText The full text receipt
 * @param file The file to save the PDF to
 * @param customerName Customer's name
 * @param machineName Machine name that was rented
 * @param startDate Rental start date
 * @param endDate Rental end date
 * @param paymentType Payment type used
 * @throws Exception if PDF generation fails
 */
private void generatePdfReceipt(String receiptText, File file, String customerName, 
                                String machineName, java.sql.Date startDate, 
                                java.sql.Date endDate, String paymentType) throws Exception {
    
    // Create document with A5 size
    Document document = new Document(PageSize.A5);
    PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
    document.open();
    
    // Add title
    com.itextpdf.text.Font titleFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 14, com.itextpdf.text.Font.BOLD);
    Paragraph title = new Paragraph("VIDEOKE RENTAL RECEIPT", titleFont);
    title.setAlignment(Element.ALIGN_CENTER);
    document.add(title);
    document.add(Chunk.NEWLINE);
    
    // Add business info
    com.itextpdf.text.Font normalFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10);
    com.itextpdf.text.Font boldFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.HELVETICA, 10, com.itextpdf.text.Font.BOLD);
    
    Paragraph businessInfo = new Paragraph();
    businessInfo.setAlignment(Element.ALIGN_CENTER);
    businessInfo.add(new Chunk("Videoke Rental System\n", boldFont));
    businessInfo.add(new Chunk("123 Main Street, Your City\n", normalFont));
    businessInfo.add(new Chunk("Phone: (123) 456-7890\n", normalFont));
    businessInfo.add(new Chunk("Email: contact@vidokerental.com\n", normalFont));
    document.add(businessInfo);
    
    // Add separator line
    LineSeparator ls = new LineSeparator();
    ls.setLineWidth(1);
    document.add(new Chunk(ls));
    document.add(Chunk.NEWLINE);
    
    // Add receipt details
    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    PdfPTable detailsTable = new PdfPTable(2);
    detailsTable.setWidthPercentage(100);
    detailsTable.setSpacingAfter(10f);
    
    // Add receipt details in table format
    addTableRow(detailsTable, "Receipt Date:", dateTimeFormatter.format(new java.util.Date()), boldFont, normalFont);
    addTableRow(detailsTable, "Customer Name:", customerName, boldFont, normalFont);
    addTableRow(detailsTable, "Machine:", machineName, boldFont, normalFont);
    addTableRow(detailsTable, "Rental Period:", dateFormatter.format(startDate) + " to " + dateFormatter.format(endDate), boldFont, normalFont);
    addTableRow(detailsTable, "Status:", "Pickup", boldFont, normalFont);
    addTableRow(detailsTable, "Payment Type:", paymentType, boldFont, normalFont);
    document.add(detailsTable);
    
    // Add the complete receipt text
    com.itextpdf.text.Font receiptFont = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 9);
    
    // Format the receipt text by extracting key parts
    String[] lines = receiptText.split("\n");
    StringBuilder formattedText = new StringBuilder();
    
    // Skip the header lines we've already formatted above
    boolean contentStarted = false;
    for (String line : lines) {
        if (line.contains("Additional Items:") || line.contains("---------------------------------")) {
            contentStarted = true;
        }
        
        if (contentStarted) {
            formattedText.append(line).append("\n");
        }
    }
    
    Paragraph receiptContent = new Paragraph(formattedText.toString(), receiptFont);
    document.add(receiptContent);
    document.add(Chunk.NEWLINE);
    
    // Add terms and conditions
    Paragraph terms = new Paragraph("Terms & Conditions:", boldFont);
    document.add(terms);
    
    com.itextpdf.text.List conditions = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
    conditions.setListSymbol("• ");
    conditions.add(new ListItem("Any damage or loss will be the client's responsibility", normalFont));
    conditions.add(new ListItem("Late returns may incur additional fees", normalFont));
    conditions.add(new ListItem("Fees are non-refundable once the equipment is picked up", normalFont));
    document.add(conditions);
    
    // Add footer
    document.add(Chunk.NEWLINE);
    Paragraph footer = new Paragraph("Thank you for your business!", boldFont);
    footer.setAlignment(Element.ALIGN_CENTER);
    document.add(footer);
    
    document.close();
}

/**
 * Helper method to add rows to a PDF table
 */
private void addTableRow(PdfPTable table, String label, String value, Font labelFont, Font valueFont) {
    PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
    labelCell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    labelCell.setHorizontalAlignment(Element.ALIGN_LEFT);
    
    PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
    valueCell.setBorder(com.itextpdf.text.Rectangle.NO_BORDER);
    valueCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    
    table.addCell(labelCell);
    table.addCell(valueCell);
}

    private void cusnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cusnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cusnameActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailActionPerformed

    private void connumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connumActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_connumActionPerformed

    private void addsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addsActionPerformed

    private void jLabel24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel24MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel24MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        FlatMacLightLaf.setup();
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Staff.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Staff().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adds;
    private javax.swing.JButton booking;
    private javax.swing.JTextField connum;
    private javax.swing.JTextField cusname;
    private javax.swing.JButton customermngmnt;
    private javax.swing.JButton dashboard;
    private javax.swing.JTextField email;
    private java.awt.TextArea info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logoutstaff;
    private javax.swing.JButton machine1bttn1;
    private javax.swing.JButton machine2bttn1;
    private javax.swing.JButton machine3bttn1;
    private javax.swing.JButton machine4bttn1;
    private javax.swing.JButton machine5bttn1;
    private javax.swing.JButton machine6bttn1;
    private javax.swing.JButton mngequipment;
    private javax.swing.JButton mngreservation;
    private javax.swing.JButton rent;
    private javax.swing.JButton usagemonitor;
    // End of variables declaration//GEN-END:variables
}
