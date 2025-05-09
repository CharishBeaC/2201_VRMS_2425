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
import java.text.SimpleDateFormat;
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
// Swing UI components
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

// JFreeChart imports for the dashboard charts
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.Date;

// Swing UI components
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

// JFreeChart imports for the dashboard charts
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import com.itextpdf.text.Image;



/**
 *
 * @author User
 */
public class Staff extends javax.swing.JFrame {
    private Connection kon;
    private JTable machineTable;
    // Class fields for animation
private List<JLabel> statCardLabels;
private List<Double> statCardValues;
private DefaultCategoryDataset revenueChartDataset;
private Map<String, Double> revenueChartValues;
private DefaultPieDataset machinesPieDataset;
private Map<String, Integer> machinesPieValues;
private DefaultCategoryDataset customersChartDataset;
private Map<String, Integer> customersChartValues;
private List<JLabel> predictionLabels;
private List<Double> predictionValues; // Add a field to store the machine table reference

    /**
     *
     */
    // Add this to your constructor after the rest of your initialization code

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
    
    // Initialize customer management panel
    initCustomerManagementPanel();
    
    // Load customer data
    loadCustomerData();
    
    // Initialize reservation management panel
    initReservationManagementPanel();
   
    initMachineManagementPanel();
    initDashboard();
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

    private void initDashboard() {
        // Clear the panel
    dashboardpanel.removeAll();
    dashboardpanel.setLayout(new BorderLayout());
    dashboardpanel.setBackground(new Color(240, 248, 255));

    // Create a main panel with padding
    JPanel mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.setBackground(new Color(240, 248, 255));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    // Create header panel with BorderLayout to position items left and right
    JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
    headerPanel.setOpaque(false);
    headerPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    headerPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));

    // Create dashboard title for LEFT side
    JLabel titleLabel = new JLabel("System Dashboard");
    titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
    titleLabel.setForeground(new Color(33, 33, 33));

    // Create the Generate Analytics Report button for RIGHT side
    JButton generateReportButton = new JButton("Generate Analytics Report");
    generateReportButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
    generateReportButton.setBackground(new Color(33, 150, 243));
    generateReportButton.setForeground(Color.WHITE);
    generateReportButton.setFocusPainted(false);
    generateReportButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
    generateReportButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    generateReportButton.addActionListener(e -> generateAnalyticsReport());
    
    // Add components to the header panel
    headerPanel.add(titleLabel, BorderLayout.WEST);
    headerPanel.add(generateReportButton, BorderLayout.EAST);
    
    // Add header panel to main panel
    mainPanel.add(headerPanel);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    // Add the stats summary cards
    JPanel statsPanel = createStatsSummaryPanel();
    statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    mainPanel.add(statsPanel);
    mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));

    // Create chart panels grid
    JPanel chartsGrid = new JPanel(new GridLayout(1, 2, 15, 0));
    chartsGrid.setOpaque(false);
    chartsGrid.setAlignmentX(Component.LEFT_ALIGNMENT);
    chartsGrid.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
    
    
        // Revenue chart
        JPanel revenueChartPanel = createRevenueChartPanel();
        revenueChartPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(33, 150, 243), 1, true),
                "Monthly Revenue",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(33, 150, 243)
            )
        ));
    
        // Top machines chart
        JPanel topMachinesPanel = createTopMachinesPanel();
        topMachinesPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 1, true),
                "Top Machines",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(76, 175, 80)
            )
        ));
    
        chartsGrid.add(revenueChartPanel);
        chartsGrid.add(topMachinesPanel);
        mainPanel.add(chartsGrid);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
    
        // Second row of charts/data
        JPanel secondRow = new JPanel(new GridLayout(1, 2, 15, 0));
        secondRow.setOpaque(false);
        secondRow.setAlignmentX(Component.LEFT_ALIGNMENT);
        secondRow.setMaximumSize(new Dimension(Integer.MAX_VALUE, 350));
    
        // Top customers panel
        JPanel topCustomersPanel = createTopCustomersPanel();
        topCustomersPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(156, 39, 176), 1, true),
                "Top Customers",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(156, 39, 176)
            )
        ));
    
        // Revenue prediction panel
        JPanel predictionPanel = createPredictionPanel();
        predictionPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 0, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(255, 152, 0), 1, true),
                "Revenue Prediction",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(255, 152, 0)
            )
        ));
    
        secondRow.add(topCustomersPanel);
        secondRow.add(predictionPanel);
        mainPanel.add(secondRow);
    
        // Create a scroll pane for the entire dashboard
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smoother scrolling
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Faster scroll speed with mouse wheel
        scrollPane.getVerticalScrollBar().setBlockIncrement(64); // Faster page up/down
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setBlockIncrement(64);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        
        
        dashboardpanel.add(scrollPane, BorderLayout.CENTER);
        dashboardpanel.revalidate();
        dashboardpanel.repaint();
        // Add this line before the final part of initDashboard() method
       

// Create a scroll pane for the entire dashboard

// ... rest of your method
        
        // Start animations
        animateStatCards();
    }

    /**
 * Generate a comprehensive analytics report in PDF format
 */
private void generateAnalyticsReport() {
    try {
        // Create file chooser for saving the report
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Analytics Report");
        fileChooser.setSelectedFile(new File("Business_Analytics_Report_" + 
                                          new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".pdf"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
        
        int userSelection = fileChooser.showSaveDialog(this);
        
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return; // User canceled
        }
        
        // Get the selected file
        File fileToSave = fileChooser.getSelectedFile();
        if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
            fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
        }
        
        // Create document and writer
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileToSave));
        document.open();
        
        // Add title
        Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.DARK_GRAY);
        Font subtitleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.DARK_GRAY);
        Font normalFont = new Font(Font.FontFamily.HELVETICA, 10, Font.NORMAL, BaseColor.BLACK);
        Font boldFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.BLACK);
        Font smallFont = new Font(Font.FontFamily.HELVETICA, 8, Font.ITALIC, new BaseColor(100, 100, 100));
        
        // Report header
        Paragraph title = new Paragraph("Business Analytics Report", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);
        
        Paragraph dateRange = new Paragraph("Generated on: " + new SimpleDateFormat("MMMM dd, yyyy").format(new Date()), normalFont);
        dateRange.setAlignment(Element.ALIGN_CENTER);
        document.add(dateRange);
        
        document.add(Chunk.NEWLINE);
        LineSeparator ls = new LineSeparator();
        document.add(ls);
        document.add(Chunk.NEWLINE);
        
        // Add summary statistics section
        Paragraph summaryTitle = new Paragraph("Summary Statistics", subtitleFont);
        document.add(summaryTitle);
        document.add(Chunk.NEWLINE);
        
        // Create summary table
        PdfPTable summaryTable = new PdfPTable(2);
        summaryTable.setWidthPercentage(100);
        summaryTable.setWidths(new float[]{1, 2});
        summaryTable.setSpacingAfter(10f);
        
        // Get the summary statistics
        int totalMachines = getTotalMachineCount();
        int rentedMachines = getRentedMachineCount();
        double monthlyRevenue = getMonthlyRevenue();
        int totalCustomers = getCustomerCount();
        double predictedMonthly = getPredictionData().get("currentMonth");
        double predictedYearly = getPredictionData().get("currentYear");
        double trend = getPredictionData().get("trend");
        
        // Format the cell style
        PdfPCell headerCell = new PdfPCell(new Phrase("Key Metrics", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        summaryTable.addCell(headerCell);
        
        headerCell = new PdfPCell(new Phrase("Values", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        summaryTable.addCell(headerCell);
        
        // Add rows to the table
        addTableRow(summaryTable, "Total Machines", String.valueOf(totalMachines), normalFont);
        addTableRow(summaryTable, "Currently Rented/Reserved", String.valueOf(rentedMachines), normalFont);
        addTableRow(summaryTable, "Available Machines", String.valueOf(totalMachines - rentedMachines), normalFont);
        addTableRow(summaryTable, "Utilization Rate", String.format("%.1f%%", ((double)rentedMachines/totalMachines)*100), normalFont);
        addTableRow(summaryTable, "Monthly Revenue", String.format("₱%.2f", monthlyRevenue), normalFont);
        addTableRow(summaryTable, "Total Customers", String.valueOf(totalCustomers), normalFont);
        addTableRow(summaryTable, "Revenue Trend", String.format("%.1f%%", trend), normalFont);
        addTableRow(summaryTable, "Monthly Revenue Forecast", String.format("₱%.2f", predictedMonthly), normalFont);
        addTableRow(summaryTable, "Yearly Revenue Forecast", String.format("₱%.2f", predictedYearly), normalFont);
        
        document.add(summaryTable);
        document.add(Chunk.NEWLINE);
        
        // Add monthly revenue chart section
        Paragraph revenueChartTitle = new Paragraph("Monthly Revenue Analysis", subtitleFont);
        document.add(revenueChartTitle);
        document.add(Chunk.NEWLINE);
        
        // Get revenue data and create a chart image
        Map<String, Double> revenueData = getMonthlyRevenueData();
        JFreeChart revenueChart = createRevenueChartForReport(revenueData);
        document.add(createImageFromChart(revenueChart, 500, 300));
        
        Paragraph revenueNote = new Paragraph("Revenue trend over the past months showing business performance.", smallFont);
        revenueNote.setAlignment(Element.ALIGN_CENTER);
        document.add(revenueNote);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        // Add top machines section
        Paragraph machinesTitle = new Paragraph("Top Performing Machines", subtitleFont);
        document.add(machinesTitle);
        document.add(Chunk.NEWLINE);
        
        // Add top machines table
        PdfPTable machinesTable = new PdfPTable(3);
        machinesTable.setWidthPercentage(100);
        machinesTable.setWidths(new float[]{2, 1, 1});
        machinesTable.setSpacingAfter(10f);
        
        // Add headers
        headerCell = new PdfPCell(new Phrase("Machine Name", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        machinesTable.addCell(headerCell);
        
        headerCell = new PdfPCell(new Phrase("Rental Count", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        machinesTable.addCell(headerCell);
        
        headerCell = new PdfPCell(new Phrase("Popularity", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        machinesTable.addCell(headerCell);
        
        // Get top machines data
        Map<String, Integer> topMachines = getTopMachines(5);
        int totalRentals = topMachines.values().stream().mapToInt(Integer::intValue).sum();
        
        // Add machines to table with percentage
        for (Map.Entry<String, Integer> entry : topMachines.entrySet()) {
            machinesTable.addCell(new Phrase(entry.getKey(), normalFont));
            machinesTable.addCell(new Phrase(entry.getValue().toString(), normalFont));
            double percentage = totalRentals > 0 ? (entry.getValue() * 100.0) / totalRentals : 0;
            machinesTable.addCell(new Phrase(String.format("%.1f%%", percentage), normalFont));
        }
        
        document.add(machinesTable);
        document.add(Chunk.NEWLINE);
        
        // Add top customers section
        Paragraph customersTitle = new Paragraph("Top Customers", subtitleFont);
        document.add(customersTitle);
        document.add(Chunk.NEWLINE);
        
        // Add top customers table
        PdfPTable customersTable = new PdfPTable(3);
        customersTable.setWidthPercentage(100);
        customersTable.setWidths(new float[]{2, 1, 1});
        customersTable.setSpacingAfter(10f);
        
        // Add headers
        headerCell = new PdfPCell(new Phrase("Customer Name", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        customersTable.addCell(headerCell);
        
        headerCell = new PdfPCell(new Phrase("Rental Count", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        customersTable.addCell(headerCell);
        
        headerCell = new PdfPCell(new Phrase("Customer Value", boldFont));
        headerCell.setBackgroundColor(new BaseColor(240, 240, 240));
        headerCell.setPadding(5);
        customersTable.addCell(headerCell);
        
        // Get top customers data
        Map<String, Integer> topCustomers = getTopCustomers(5);
        for (Map.Entry<String, Integer> entry : topCustomers.entrySet()) {
            customersTable.addCell(new Phrase(entry.getKey(), normalFont));
            customersTable.addCell(new Phrase(entry.getValue().toString(), normalFont));
            String value = entry.getValue() > 10 ? "High" : 
                          (entry.getValue() > 5 ? "Medium" : "Regular");
            customersTable.addCell(new Phrase(value, normalFont));
        }
        
        document.add(customersTable);
        document.add(Chunk.NEWLINE);
        
        // Add business insights section
        Paragraph insightsTitle = new Paragraph("Business Insights", subtitleFont);
        document.add(insightsTitle);
        document.add(Chunk.NEWLINE);
        
        // Add insights based on data
        com.itextpdf.text.List insights = new com.itextpdf.text.List(com.itextpdf.text.List.ORDERED);
        
        // Revenue insight
        if (trend > 5) {
            insights.add(new ListItem("Revenue is showing strong growth (" + String.format("%.1f%%", trend) + 
                                    "), indicating effective business strategies.", normalFont));
        } else if (trend > 0) {
            insights.add(new ListItem("Revenue is growing steadily (" + String.format("%.1f%%", trend) + 
                                    "), suggesting stable business performance.", normalFont));
        } else {
            insights.add(new ListItem("Revenue is showing a decline (" + String.format("%.1f%%", trend) + 
                                    "). Consider promotion strategies to boost rentals.", normalFont));
        }
        
        // Machine utilization insight
        double utilizationRate = ((double)rentedMachines/totalMachines)*100;
        if (utilizationRate > 80) {
            insights.add(new ListItem("High machine utilization rate (" + String.format("%.1f%%", utilizationRate) + 
                                    "). Consider investing in more machines to meet demand.", normalFont));
        } else if (utilizationRate > 50) {
            insights.add(new ListItem("Good machine utilization rate (" + String.format("%.1f%%", utilizationRate) + 
                                    "), indicating balanced inventory management.", normalFont));
        } else {
            insights.add(new ListItem("Low machine utilization rate (" + String.format("%.1f%%", utilizationRate) + 
                                    "). Explore marketing strategies to increase machine rentals.", normalFont));
        }
        
        // Customer retention insight
        if (totalCustomers > 100) {
            insights.add(new ListItem("Strong customer base with " + totalCustomers + 
                                    " total customers. Consider loyalty programs to maintain retention.", normalFont));
        } else {
            insights.add(new ListItem("Growing customer base with " + totalCustomers + 
                                    " customers. Focus on customer acquisition strategies.", normalFont));
        }
        
        document.add(insights);
        document.add(Chunk.NEWLINE);
        
        // Add recommendations section
        Paragraph recsTitle = new Paragraph("Recommendations", subtitleFont);
        document.add(recsTitle);
        document.add(Chunk.NEWLINE);
        
        com.itextpdf.text.List recommendations = new com.itextpdf.text.List(com.itextpdf.text.List.UNORDERED);
        recommendations.setListSymbol("• ");
        
        // Add dynamic recommendations based on data
        if (utilizationRate < 50) {
            recommendations.add(new ListItem("Implement promotional pricing for less popular machines to increase utilization.", normalFont));
        }
        
        if (trend < 0) {
            recommendations.add(new ListItem("Review pricing strategy and consider seasonal promotions to boost revenue.", normalFont));
        }
        
        recommendations.add(new ListItem("Focus marketing efforts on the highest performing machines: " + 
                                       getTopMachineName() + ".", normalFont));
        recommendations.add(new ListItem("Develop a loyalty program for returning customers to increase retention.", normalFont));
        recommendations.add(new ListItem("Consider targeted promotions for weekday rentals when utilization is typically lower.", normalFont));
        
        document.add(recommendations);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        
        // Add footer with report generation info
        Paragraph footer = new Paragraph("Report generated by Videoke Rental System Management on " +
                                        new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm a").format(new Date()), smallFont);
        footer.setAlignment(Element.ALIGN_CENTER);
        document.add(footer);
        
        document.close();
        
        // Show success message with option to open the file
        int openFile = JOptionPane.showConfirmDialog(this,
                "Analytics report created successfully!\nWould you like to open the file?",
                "Report Generated",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
                
        if (openFile == JOptionPane.YES_OPTION) {
            try {
                Desktop.getDesktop().open(fileToSave);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,
                    "Could not open the file automatically. File is saved at:\n" + fileToSave.getAbsolutePath(),
                    "Cannot Open File",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this,
            "Error generating report: " + e.getMessage(),
            "Report Error",
            JOptionPane.ERROR_MESSAGE);
    }
}

/**
 * Create a revenue chart specifically for the PDF report
 */
private JFreeChart createRevenueChartForReport(Map<String, Double> revenueData) {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    // Sort the keys (months) to ensure they're in order
    List<String> months = new ArrayList<>(revenueData.keySet());
    Collections.sort(months, (m1, m2) -> {
        // Assuming month format is "MMM yyyy"
        try {
            SimpleDateFormat format = new SimpleDateFormat("MMM yyyy");
            Date date1 = format.parse(m1);
            Date date2 = format.parse(m2);
            return date1.compareTo(date2);
        } catch (ParseException e) {
            return m1.compareTo(m2);
        }
    });
    
    // Add sorted data to dataset with actual values (not starting at 0)
    for (String month : months) {
        dataset.addValue(revenueData.get(month), "Revenue", month);
    }
    
    // Create chart with better styling for PDF
    JFreeChart chart = ChartFactory.createBarChart(
        "Monthly Revenue", // Title
        "Month", // X-Axis Label
        "Revenue (₱)", // Y-Axis Label
        dataset,
        PlotOrientation.VERTICAL,
        false, // Show legend
        true, // Use tooltips
        false // Generate URLs
    );
    
    // Customize the chart for better PDF appearance
    chart.setBackgroundPaint(Color.WHITE);
    CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.WHITE);
    plot.setRangeGridlinePaint(new Color(230, 230, 230));
    plot.setDomainGridlinesVisible(true);
    plot.setDomainGridlinePaint(new Color(230, 230, 230));
    
    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setSeriesPaint(0, new Color(33, 150, 243));
    renderer.setBarPainter(new StandardBarPainter());
    renderer.setShadowVisible(false);
    
    CategoryAxis domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryMargin(0.2);
    domainAxis.setLowerMargin(0.05);
    domainAxis.setUpperMargin(0.05);
    
    return chart;
}

/**
 * Convert a JFreeChart to an Image for inclusion in the PDF
 */
private Image createImageFromChart(JFreeChart chart, int width, int height) throws DocumentException {
    BufferedImage bufferedImage = chart.createBufferedImage(width, height);
    try {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        byte[] imageBytes = baos.toByteArray();
        
        com.itextpdf.text.Image pdfImage = com.itextpdf.text.Image.getInstance(imageBytes);
        pdfImage.setAlignment(Element.ALIGN_CENTER);
        return pdfImage;
    } catch (IOException e) {
        throw new DocumentException(e.getMessage());
    }
}

/**
 * Helper method to add rows to a PDF table
 */
private void addTableRow(PdfPTable table, String label, String value, Font font) {
    PdfPCell cell = new PdfPCell(new Phrase(label, font));
    cell.setPadding(5);
    table.addCell(cell);
    
    cell = new PdfPCell(new Phrase(value, font));
    cell.setPadding(5);
    table.addCell(cell);
}

/**
 * Get the name of the top performing machine
 */
private String getTopMachineName() {
    Map<String, Integer> topMachines = getTopMachines(1);
    if (!topMachines.isEmpty()) {
        return topMachines.keySet().iterator().next();
    }
    return "N/A";
}
    
private void addAnalyticsReportButton() {
    // Create a button panel at the bottom of the dashboard
    JPanel reportButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
    reportButtonPanel.setOpaque(false);
    reportButtonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
    reportButtonPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
    
    // Create a stylish report button
    JButton generateReportButton = new JButton("Generate Analytics Report");
    generateReportButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    generateReportButton.setBackground(new Color(33, 150, 243));
    generateReportButton.setForeground(Color.WHITE);
    generateReportButton.setFocusPainted(false);
    generateReportButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    generateReportButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    
    // Add action listener to handle click
    generateReportButton.addActionListener(e -> generateAnalyticsReport());
    
    // Add button to panel
    reportButtonPanel.add(generateReportButton);
    
    // Add the button panel to main panel
   
}

    
    
    /**
     * Creates a panel with stats summary cards
     */
    private JPanel createStatsSummaryPanel() {
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 15, 0));
        statsPanel.setOpaque(false);
        statsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 120));
        
        // Get statistics from the database
        int totalMachines = getTotalMachineCount();
        int totalRented = getRentedMachineCount();
        double monthlyRevenue = getMonthlyRevenue();
        int totalCustomers = getCustomerCount();
        
        // Create stat cards with animations
        JPanel machinesCard = createStatCard("Total Machines", String.valueOf(totalMachines), 
                                          new Color(33, 150, 243), new Color(227, 242, 253));
        
        JPanel rentedCard = createStatCard("Machines Rented", String.valueOf(totalRented),
                                        new Color(76, 175, 80), new Color(232, 245, 233));
        
        JPanel revenueCard = createStatCard("Monthly Revenue", String.format("₱%.2f", monthlyRevenue),
                                        new Color(255, 152, 0), new Color(255, 243, 224));
        
        JPanel customersCard = createStatCard("Total Customers", String.valueOf(totalCustomers),
                                          new Color(156, 39, 176), new Color(243, 229, 245));
        
        // Store the value labels for animation
        statCardLabels = new ArrayList<>();
        statCardLabels.add((JLabel)machinesCard.getComponent(1));
        statCardLabels.add((JLabel)rentedCard.getComponent(1));
        statCardLabels.add((JLabel)revenueCard.getComponent(1));
        statCardLabels.add((JLabel)customersCard.getComponent(1));
        
        // Reset labels to zero for animation
        for (JLabel label : statCardLabels) {
            if (label.getText().startsWith("₱")) {
                label.setText("₱0.00");
            } else {
                label.setText("0");
            }
        }
        
        // Store the actual values for animation
        statCardValues = new ArrayList<>();
        statCardValues.add((double)totalMachines);
        statCardValues.add((double)totalRented);
        statCardValues.add(monthlyRevenue);
        statCardValues.add((double)totalCustomers);
        
        statsPanel.add(machinesCard);
        statsPanel.add(rentedCard);
        statsPanel.add(revenueCard);
        statsPanel.add(customersCard);
        
        return statsPanel;
    }
    
    /**
     * Creates a stylish stat card
     */
    private JPanel createStatCard(String title, String value, Color accentColor, Color bgColor) {
        JPanel card = new JPanel(new BorderLayout(10, 10));
        card.setBackground(bgColor);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(accentColor, 1, true),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        titleLabel.setForeground(new Color(60, 60, 60));
        
        JLabel valueLabel = new JLabel(value);
        valueLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        valueLabel.setForeground(accentColor);
        
        card.add(titleLabel, BorderLayout.NORTH);
        card.add(valueLabel, BorderLayout.CENTER);
        
        return card;
    }
    
    /**
     * Creates the revenue chart panel
     */
    private JPanel createRevenueChartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Double> revenueData = getMonthlyRevenueData();
        
        // Sort the keys (months) to ensure they're in order
        List<String> months = new ArrayList<>(revenueData.keySet());
        Collections.sort(months, (m1, m2) -> {
            // Assuming month format is "MMM yyyy"
            try {
                SimpleDateFormat format = new SimpleDateFormat("MMM yyyy");
                Date date1 = format.parse(m1);
                Date date2 = format.parse(m2);
                return date1.compareTo(date2);
            } catch (ParseException e) {
                return m1.compareTo(m2);
            }
        });
        
        // Add sorted data to dataset
        for (String month : months) {
            dataset.addValue(0.0, "Revenue", month); // Start at 0 for animation
        }
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "", // Title
            "Month", // X-Axis Label
            "Revenue (₱)", // Y-Axis Label
            dataset,
            PlotOrientation.VERTICAL,
            false, // Show legend
            true, // Use tooltips
            false // Generate URLs
        );
        
        // Customize the chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(230, 230, 230));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(230, 230, 230));
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(33, 150, 243));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(false);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.2);
        domainAxis.setLowerMargin(0.05);
        domainAxis.setUpperMargin(0.05);
        
        // Create the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setMouseWheelEnabled(true);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        // Store the dataset and actual values for animation
        revenueChartDataset = dataset;
        revenueChartValues = revenueData;
        
        return panel;
    }
    
    /**
     * Creates the top machines panel
     */
    private JPanel createTopMachinesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Create dataset
        DefaultPieDataset dataset = new DefaultPieDataset();
        Map<String, Integer> topMachinesData = getTopMachines(5); // Get top 5 machines
        
        // Initialize with zero values for animation
        for (Map.Entry<String, Integer> entry : topMachinesData.entrySet()) {
            dataset.setValue(entry.getKey(), 0); // Start at 0 for animation
        }
        
        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
            "", // Title
            dataset,
            true, // Include legend
            true, // Use tooltips
            false // Generate URLs
        );
        
        // Customize chart
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setOutlineVisible(false);
        plot.setLabelFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator(
            "{0}: {1} ({2})", // Format: name: value (percentage)
            new DecimalFormat("0"), 
            new DecimalFormat("0.0%")
        ));
        
        // Set custom colors
        Color[] colors = {
            new Color(76, 175, 80),
            new Color(33, 150, 243),
            new Color(255, 152, 0),
            new Color(156, 39, 176),
            new Color(244, 67, 54)
        };
        
        int colorIndex = 0;
        for (String key : topMachinesData.keySet()) {
            plot.setSectionPaint(key, colors[colorIndex % colors.length]);
            colorIndex++;
        }
        
        // Create the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setMouseWheelEnabled(true);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        // Store dataset and actual values for animation
        machinesPieDataset = dataset;
        machinesPieValues = topMachinesData;
        
        return panel;
    }
    
    /**
     * Creates the top customers panel
     */
    private JPanel createTopCustomersPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Create dataset
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Map<String, Integer> topCustomersData = getTopCustomers(5); // Get top 5 customers
        
        // Add data with initial zero values for animation
        for (Map.Entry<String, Integer> entry : topCustomersData.entrySet()) {
            dataset.addValue(0, "Rentals", entry.getKey()); // Start at 0 for animation
        }
        
        // Create chart
        JFreeChart chart = ChartFactory.createBarChart(
            "", // Title
            "Customer", // X-Axis Label
            "Number of Rentals", // Y-Axis Label
            dataset,
            PlotOrientation.HORIZONTAL, // Horizontal bars
            false, // Show legend
            true, // Use tooltips
            false // Generate URLs
        );
        
        // Customize chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(new Color(230, 230, 230));
        
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(156, 39, 176));
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setShadowVisible(false);
        
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setCategoryMargin(0.3);
        
        // Create the chart panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(400, 300));
        chartPanel.setMouseWheelEnabled(true);
        panel.add(chartPanel, BorderLayout.CENTER);
        
        // Store dataset and actual values for animation
        customersChartDataset = dataset;
        customersChartValues = topCustomersData;
        
        return panel;
    }
    
    /**
     * Creates the prediction panel
     */
    private JPanel createPredictionPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Get prediction data
        Map<String, Double> predictionData = getPredictionData();
        
        // Create a panel for the prediction details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Current month prediction
        JLabel currentMonthLabel = new JLabel("Current Month Forecast");
        currentMonthLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        currentMonthLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel currentMonthValue = new JLabel("₱0.00"); // Start at 0 for animation
        currentMonthValue.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        currentMonthValue.setForeground(new Color(255, 152, 0));
        currentMonthValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Current year prediction
        JLabel currentYearLabel = new JLabel("Current Year Forecast");
        currentYearLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        currentYearLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        JLabel currentYearValue = new JLabel("₱0.00"); // Start at 0 for animation
        currentYearValue.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 24));
        currentYearValue.setForeground(new Color(255, 152, 0));
        currentYearValue.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // Add spacers and components
        detailsPanel.add(currentMonthLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        detailsPanel.add(currentMonthValue);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        detailsPanel.add(currentYearLabel);
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        detailsPanel.add(currentYearValue);
        
        // Add some trend indicators
        JLabel trendLabel = new JLabel("Trend: " + predictionData.get("trend") + "%");
        trendLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 14));
        trendLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        double trend = predictionData.get("trend");
        if (trend > 0) {
            trendLabel.setForeground(new Color(76, 175, 80)); // Green for positive
        } else if (trend < 0) {
            trendLabel.setForeground(new Color(244, 67, 54)); // Red for negative
        }
        
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        detailsPanel.add(trendLabel);
        
        // Add a note about prediction basis
        JLabel noteLabel = new JLabel("<html>Based on historical data patterns<br>and current month's progress.</html>");
        noteLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 12));
        noteLabel.setForeground(new Color(100, 100, 100));
        noteLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        detailsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        detailsPanel.add(noteLabel);
        
        panel.add(detailsPanel, BorderLayout.CENTER);
        
        // Store labels and values for animation
        predictionLabels = new ArrayList<>();
        predictionLabels.add(currentMonthValue);
        predictionLabels.add(currentYearValue);
        
        predictionValues = new ArrayList<>();
        predictionValues.add(predictionData.get("currentMonth"));
        predictionValues.add(predictionData.get("currentYear"));
        
        return panel;
    }
    
    /**
     * Animate all dashboard elements
     */
    private void animateStatCards() {
        // Use a timer for animation - update every 50ms
        final javax.swing.Timer timer = new javax.swing.Timer(50, null);
        final int totalFrames = 20; // Total animation frames
        final int[] currentFrame = {0}; // Current frame counter
    
        timer.addActionListener(e -> {
            currentFrame[0]++;
            double progress = (double) currentFrame[0] / totalFrames;
            
            // Animate stat cards
            for (int i = 0; i < statCardLabels.size(); i++) {
                JLabel label = statCardLabels.get(i);
                double targetValue = statCardValues.get(i);
                double currentValue = targetValue * progress;
                
                if (label.getText().startsWith("₱")) {
                    label.setText(String.format("₱%.2f", currentValue));
                } else {
                    label.setText(String.valueOf((int)currentValue));
                }
            }
            
            // Animate revenue chart
            int revenueIdx = 0;
            for (String month : revenueChartValues.keySet()) {
                double targetValue = revenueChartValues.get(month);
                double currentValue = targetValue * progress;
                revenueChartDataset.setValue(currentValue, "Revenue", month);
                revenueIdx++;
            }
            
            // Animate pie chart
            for (String machine : machinesPieValues.keySet()) {
                int targetValue = machinesPieValues.get(machine);
                double currentValue = targetValue * progress;
                machinesPieDataset.setValue(machine, currentValue);
            }
            
            // Animate customer chart
            for (String customer : customersChartValues.keySet()) {
                int targetValue = customersChartValues.get(customer);
                double currentValue = targetValue * progress;
                customersChartDataset.setValue(currentValue, "Rentals", customer);
            }
            
            // Animate prediction values
            for (int i = 0; i < predictionLabels.size(); i++) {
                JLabel label = predictionLabels.get(i);
                double targetValue = predictionValues.get(i);
                double currentValue = targetValue * progress;
                label.setText(String.format("₱%.2f", currentValue));
            }
            
            if (currentFrame[0] >= totalFrames) {
                timer.stop();
                
                // Set the final exact values
                for (int i = 0; i < statCardLabels.size(); i++) {
                    JLabel label = statCardLabels.get(i);
                    double targetValue = statCardValues.get(i);
                    
                    if (label.getText().startsWith("₱")) {
                        label.setText(String.format("₱%.2f", targetValue));
                    } else {
                        label.setText(String.valueOf((int)targetValue));
                    }
                }
                
                // Set final prediction values
                for (int i = 0; i < predictionLabels.size(); i++) {
                    JLabel label = predictionLabels.get(i);
                    double targetValue = predictionValues.get(i);
                    label.setText(String.format("₱%.2f", targetValue));
                }
            }
        });
        
        timer.start();
    }
    
    /**
     * Get monthly revenue data from database
     */
    private Map<String, Double> getMonthlyRevenueData() {
        Map<String, Double> revenueData = new LinkedHashMap<>();
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            // If connection fails, return dummy data
            revenueData.put("Jan 2025", 25000.0);
            revenueData.put("Feb 2025", 28000.0);
            revenueData.put("Mar 2025", 27000.0);
            revenueData.put("Apr 2025", 32000.0);
            revenueData.put("May 2025", 35000.0);
            return revenueData;
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT DATE_FORMAT(r.rental_start_date, '%b %Y') AS month, " +
                "SUM(p.total_amount) as revenue " +
                "FROM rental_logs r " +
                "JOIN payments p ON r.log_id = p.log_id " +
                "WHERE r.rental_start_date >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH) " +
                "GROUP BY DATE_FORMAT(r.rental_start_date, '%b %Y') " +
                "ORDER BY MIN(r.rental_start_date)"
            );
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String month = rs.getString("month");
                double revenue = rs.getDouble("revenue");
                revenueData.put(month, revenue);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Add fallback data if query fails
            revenueData.put("Jan 2025", 25000.0);
            revenueData.put("Feb 2025", 28000.0);
            revenueData.put("Mar 2025", 27000.0);
            revenueData.put("Apr 2025", 32000.0);
            revenueData.put("May 2025", 35000.0);
        }
        
        return revenueData;
    }
    
    /**
     * Get top machines by rental count
     */
    private Map<String, Integer> getTopMachines(int limit) {
        Map<String, Integer> topMachines = new LinkedHashMap<>();
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            // If connection fails, return dummy data
            topMachines.put("Mega Videoke", 45);
            topMachines.put("Pro Karaoke", 32);
            topMachines.put("Party Speaker", 28);
            topMachines.put("Stage Lite", 20);
            topMachines.put("Sound Max", 15);
            return topMachines;
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT machine_name, COUNT(*) as rental_count " +
                "FROM rental_logs " +
                "GROUP BY machine_name " +
                "ORDER BY rental_count DESC " +
                "LIMIT ?"
            );
            stmt.setInt(1, limit);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String machineName = rs.getString("machine_name");
                int rentalCount = rs.getInt("rental_count");
                topMachines.put(machineName, rentalCount);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Add fallback data if query fails
            topMachines.put("Mega Videoke", 45);
            topMachines.put("Pro Karaoke", 32);
            topMachines.put("Party Speaker", 28);
            topMachines.put("Stage Lite", 20);
            topMachines.put("Sound Max", 15);
        }
        
        return topMachines;
    }
    
    /**
     * Get top customers by rental count
     */
    private Map<String, Integer> getTopCustomers(int limit) {
        Map<String, Integer> topCustomers = new LinkedHashMap<>();
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            // If connection fails, return dummy data
            topCustomers.put("John Smith", 12);
            topCustomers.put("Maria Garcia", 9);
            topCustomers.put("Robert Johnson", 8);
            topCustomers.put("Sarah Williams", 7);
            topCustomers.put("David Lee", 5);
            return topCustomers;
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT customer_name, COUNT(*) as rental_count " +
                "FROM rental_logs " +
                "GROUP BY customer_name " +
                "ORDER BY rental_count DESC " +
                "LIMIT ?"
            );
            stmt.setInt(1, limit);
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String customerName = rs.getString("customer_name");
                int rentalCount = rs.getInt("rental_count");
                topCustomers.put(customerName, rentalCount);
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Add fallback data if query fails
            topCustomers.put("John Smith", 12);
            topCustomers.put("Maria Garcia", 9);
            topCustomers.put("Robert Johnson", 8);
            topCustomers.put("Sarah Williams", 7);
            topCustomers.put("David Lee", 5);
        }
        
        return topCustomers;
    }
    
    /**
     * Get prediction data for current month and year
     */
    private Map<String, Double> getPredictionData() {
        Map<String, Double> predictionData = new HashMap<>();
        Connection conn = DBKonek.getConnection();
        
        // Default values in case of query failure
        double currentMonthPrediction = 42000.0;
        double currentYearPrediction = 360000.0;
        double trendPercentage = 5.3;
        
        if (conn != null) {
            try {
                // Get average daily revenue for this month so far
                PreparedStatement dailyStmt = conn.prepareStatement(
                    "SELECT AVG(daily_revenue) as avg_daily_revenue, " +
                    "COUNT(DISTINCT date_day) as days_with_data " +
                    "FROM (" +
                    "  SELECT DATE(r.rental_start_date) as date_day, SUM(p.total_amount) as daily_revenue " +
                    "  FROM rental_logs r " +
                    "  JOIN payments p ON r.log_id = p.log_id " +
                    "  WHERE MONTH(r.rental_start_date) = MONTH(CURDATE()) " +
                    "  AND YEAR(r.rental_start_date) = YEAR(CURDATE()) " +
                    "  GROUP BY DATE(r.rental_start_date)" +
                    ") daily_revenue"
                );
                
                ResultSet dailyRs = dailyStmt.executeQuery();
                
                if (dailyRs.next()) {
                    double avgDailyRevenue = dailyRs.getDouble("avg_daily_revenue");
                    int daysWithData = dailyRs.getInt("days_with_data");
                    
                    if (daysWithData > 0 && avgDailyRevenue > 0) {
                        // Get days in current month
                        Calendar cal = Calendar.getInstance();
                        int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                        
                        // Predict rest of month based on daily average
                        currentMonthPrediction = avgDailyRevenue * daysInMonth;
                    }
                }
                
                // Get trend by comparing with previous month
                PreparedStatement trendStmt = conn.prepareStatement(
                    "SELECT " +
                    "  SUM(CASE WHEN MONTH(r.rental_start_date) = MONTH(CURDATE()) AND " +
                    "                YEAR(r.rental_start_date) = YEAR(CURDATE()) " +
                    "           THEN p.total_amount ELSE 0 END) as current_month, " +
                    "  SUM(CASE WHEN MONTH(r.rental_start_date) = MONTH(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) AND " +
                    "                YEAR(r.rental_start_date) = YEAR(DATE_SUB(CURDATE(), INTERVAL 1 MONTH)) " +
                    "           THEN p.total_amount ELSE 0 END) as prev_month " +
                    "FROM rental_logs r " +
                    "JOIN payments p ON r.log_id = p.log_id " +
                    "WHERE r.rental_start_date >= DATE_SUB(CURDATE(), INTERVAL 2 MONTH)"
                );
                
                ResultSet trendRs = trendStmt.executeQuery();
                
                if (trendRs.next()) {
                    double currentMonth = trendRs.getDouble("current_month");
                    double prevMonth = trendRs.getDouble("prev_month");
                    
                    if (prevMonth > 0) {
                        // calculate trend percentage
                        trendPercentage = ((currentMonth / prevMonth) - 1) * 100;
                    }
                }
                
                // Get yearly prediction by extrapolating from data so far
                PreparedStatement yearStmt = conn.prepareStatement(
                    "SELECT " +
                    "  SUM(p.total_amount) as year_to_date, " +
                    "  DAYOFYEAR(CURDATE()) as day_of_year " +
                    "FROM rental_logs r " +
                    "JOIN payments p ON r.log_id = p.log_id " +
                    "WHERE YEAR(r.rental_start_date) = YEAR(CURDATE())"
                );
                
                ResultSet yearRs = yearStmt.executeQuery();
                
                if (yearRs.next()) {
                    double yearToDate = yearRs.getDouble("year_to_date");
                    int dayOfYear = yearRs.getInt("day_of_year");
                    
                    if (dayOfYear > 0 && yearToDate > 0) {
                        // Project for full year based on current run rate
                        currentYearPrediction = yearToDate * (365.0 / dayOfYear);
                    }
                }
                
                dailyRs.close();
                dailyStmt.close();
                trendRs.close();
                trendStmt.close();
                yearRs.close();
                yearStmt.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
                // We'll use the default values
            }
        }
        
        // Store the values
        predictionData.put("currentMonth", currentMonthPrediction);
        predictionData.put("currentYear", currentYearPrediction);
        predictionData.put("trend", trendPercentage);
        
        return predictionData;
    }
    
    /**
     * Get the current month's total revenue
     */
    private double getMonthlyRevenue() {
        double revenue = 0.0;
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            return 35000.0; // Default value if connection fails
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT SUM(p.total_amount) as monthly_revenue " +
                "FROM rental_logs r " +
                "JOIN payments p ON r.log_id = p.log_id " +
                "WHERE MONTH(r.rental_start_date) = MONTH(CURDATE()) " +
                "AND YEAR(r.rental_start_date) = YEAR(CURDATE())"
            );
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                revenue = rs.getDouble("monthly_revenue");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            revenue = 35000.0; // Default value if query fails
        }
        
        return revenue;
    }
    
    /**
     * Get the total number of machines
     */
    private int getTotalMachineCount() {
        int count = 0;
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            return 25; // Default value if connection fails
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) as machine_count FROM videoke_machines"
            );
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("machine_count");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            count = 25; // Default value if query fails
        }
        
        return count;
    }
    
    /**
     * Get the count of rented machines
     */
    private int getRentedMachineCount() {
        int count = 0;
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            return 12; // Default value if connection fails
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(*) as rented_count FROM videoke_machines " +
                "WHERE status = 'Rented' OR status = 'Reserved'"
            );
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("rented_count");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            count = 12; // Default value if query fails
        }
        
        return count;
    }
    
    /**
     * Get the total customer count
     */
    private int getCustomerCount() {
        int count = 0;
        Connection conn = DBKonek.getConnection();
        
        if (conn == null) {
            return 75; // Default value if connection fails
        }
        
        try {
            PreparedStatement stmt = conn.prepareStatement(
                "SELECT COUNT(DISTINCT customer_name) as customer_count FROM rental_logs"
            );
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("customer_count");
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            count = 75; // Default value if query fails
        }
        
        return count;
    }
    
    

    private void initMachineManagementPanel() {
        // Clear existing components
        managemahcines.removeAll();
        
        // Set up a main panel with a vertical BoxLayout to stack components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(240, 248, 255));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        // --- SECTION 1: MACHINE MONITORING ---
        JPanel monitoringPanel = new JPanel();
        monitoringPanel.setLayout(new BoxLayout(monitoringPanel, BoxLayout.Y_AXIS));
        monitoringPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 15, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 212), 1, true),
                "Machine Status Monitor",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(0, 120, 212)
            )
        ));
        monitoringPanel.setBackground(new Color(240, 248, 255));
        monitoringPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        monitoringPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 320)); // Limit height
        
        // Create a panel for machine status cards
        JPanel machinesPanel = new JPanel(new GridLayout(1, 0, 20, 20));
        machinesPanel.setBackground(new Color(240, 248, 255));
        
        // Create scroll pane for machines - horizontal scrolling
        JScrollPane machinesScrollPane = new JScrollPane(machinesPanel);
        machinesScrollPane.setBorder(null);
        machinesScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        machinesScrollPane.getVerticalScrollBar().setUnitIncrement(16);  // Faster scroll speed with mouse wheel
        machinesScrollPane.getVerticalScrollBar().setBlockIncrement(64); // Faster page up/down
        machinesScrollPane.getHorizontalScrollBar().setUnitIncrement(16);
        machinesScrollPane.getHorizontalScrollBar().setBlockIncrement(64);
        machinesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        machinesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        machinesScrollPane.setPreferredSize(new Dimension(700, 200));
        machinesScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        
        // Load machines into the monitoring panel
        loadMachinesToMonitor(machinesPanel);
        
        // Add scrollPane directly to monitoringPanel - REMOVED REFRESH BUTTON
        monitoringPanel.add(machinesScrollPane);
        
        // --- SECTION 2: MACHINE MANAGEMENT ---
        // Create a split pane to separate table and form
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        splitPane.setDividerLocation(250);
        splitPane.setDividerSize(5);
        splitPane.setContinuousLayout(true);
        splitPane.setBorder(null);
        splitPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        
        // --- TOP PANEL: Machine Table ---
        JPanel tablePanel = new JPanel(new BorderLayout(10, 10));
        tablePanel.setBackground(new Color(240, 248, 255));
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(0, 0, 5, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(0, 120, 212), 1, true),
                "Machine Inventory",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(0, 120, 212)
            )
        ));
        
        // Create table model with proper column names
        DefaultTableModel machineModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Machine Name", "Status", "Price", "Remarks", "Inclusions"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0) return Integer.class;
                if (columnIndex == 3) return Double.class;
                return String.class;
            }
        };
        
        JTable machineTable = new JTable(machineModel);
        machineTable.setRowHeight(28);
        machineTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scrolling
        machineTable.setShowGrid(true);
        machineTable.setGridColor(new Color(230, 230, 230));
        machineTable.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        machineTable.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        machineTable.getTableHeader().setOpaque(false);
        machineTable.getTableHeader().setBackground(new Color(240, 240, 240));
        machineTable.setSelectionBackground(new Color(232, 242, 254));
        machineTable.setSelectionForeground(Color.BLACK);
        
        // Set column widths
        machineTable.getColumnModel().getColumn(0).setPreferredWidth(60);    // ID
        machineTable.getColumnModel().getColumn(1).setPreferredWidth(200);   // Machine Name
        machineTable.getColumnModel().getColumn(2).setPreferredWidth(100);   // Status
        machineTable.getColumnModel().getColumn(3).setPreferredWidth(100);   // Price
        machineTable.getColumnModel().getColumn(4).setPreferredWidth(250);   // Remarks
        machineTable.getColumnModel().getColumn(5).setPreferredWidth(400);   // Inclusions
        
        // Add scrolling to the table
        JScrollPane tableScrollPane = new JScrollPane(machineTable);
        tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Create search panel at top with table action buttons
        JPanel searchPanel = new JPanel(new BorderLayout(10, 0));
        searchPanel.setBackground(new Color(240, 248, 255));
        
        // Left side search controls
        JPanel searchControlsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchControlsPanel.setBackground(new Color(240, 248, 255));
        
        JLabel searchLabel = new JLabel("Search: ");
        searchLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        JTextField searchField = new JTextField(20);
        searchField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        JComboBox<String> statusFilter = new JComboBox<>(new String[] {
            "All", "Available", "Reserved", "Rented", "Not Available"
        });
        statusFilter.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        statusFilter.setPreferredSize(new Dimension(150, 25));
        
        searchControlsPanel.add(searchLabel);
        searchControlsPanel.add(searchField);
        searchControlsPanel.add(new JLabel("  Status: "));
        searchControlsPanel.add(statusFilter);
        
        // Right side table action buttons
        JPanel tableButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        tableButtonsPanel.setBackground(new Color(240, 248, 255));
        
        // Create styled buttons for the table actions
        JButton refreshButton = new JButton("Refresh");
        refreshButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        refreshButton.setBackground(new Color(240, 240, 240));
        refreshButton.addActionListener(e -> loadMachinesData(machineModel));
        
        JButton addTableButton = new JButton("Add New");
        addTableButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        addTableButton.setBackground(new Color(76, 175, 80));
        addTableButton.setForeground(Color.WHITE);
        addTableButton.setFocusPainted(false);
        
        JButton editTableButton = new JButton("Edit");
        editTableButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        editTableButton.setBackground(new Color(33, 150, 243));
        editTableButton.setForeground(Color.WHITE);
        editTableButton.setFocusPainted(false);
        
        JButton deleteTableButton = new JButton("Delete");
        deleteTableButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        deleteTableButton.setBackground(new Color(244, 67, 54));
        deleteTableButton.setForeground(Color.WHITE);
        deleteTableButton.setFocusPainted(false);
        
        tableButtonsPanel.add(addTableButton);
        tableButtonsPanel.add(Box.createHorizontalStrut(5));
        tableButtonsPanel.add(editTableButton);
        tableButtonsPanel.add(Box.createHorizontalStrut(5));
        tableButtonsPanel.add(deleteTableButton);
        tableButtonsPanel.add(Box.createHorizontalStrut(10));
        tableButtonsPanel.add(refreshButton);
        
        // Add panels to search panel
        searchPanel.add(searchControlsPanel, BorderLayout.WEST);
        searchPanel.add(tableButtonsPanel, BorderLayout.EAST);
        
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        
        // --- BOTTOM PANEL: Machine Form ---
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        formPanel.setBackground(new Color(240, 248, 255));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(5, 0, 0, 0),
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(76, 175, 80), 1, true),
                "Machine Details",
                TitledBorder.LEADING,
                TitledBorder.TOP,
                new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14),
                new Color(76, 175, 80)
            )
        ));
        
        // Form fields
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBackground(new Color(240, 248, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Machine ID - hidden/disabled
        JLabel idLabel = new JLabel("Machine ID:");
        idLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JTextField idField = new JTextField();
        idField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        idField.setEditable(false);
        idField.setBackground(new Color(240, 240, 240));
        
        // Machine Name
        JLabel nameLabel = new JLabel("Machine Name:");
        nameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JTextField nameField = new JTextField();
        nameField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Status
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JComboBox<String> statusCombo = new JComboBox<>(new String[] {
            "Available", "Reserved", "Rented", "Not Available"
        });
        statusCombo.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Price
        JLabel priceLabel = new JLabel("Price (per day):");
        priceLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JTextField priceField = new JTextField();
        priceField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Remarks
        JLabel remarksLabel = new JLabel("Remarks:");
        remarksLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JTextArea remarksArea = new JTextArea(3, 20);
        remarksArea.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        remarksArea.setLineWrap(true);
        remarksArea.setWrapStyleWord(true);
        JScrollPane remarksScroll = new JScrollPane(remarksArea);
        
        // Inclusions
        JLabel inclusionsLabel = new JLabel("Inclusions:");
        inclusionsLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JTextArea inclusionsArea = new JTextArea(4, 20);
        inclusionsArea.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        inclusionsArea.setLineWrap(true);
        inclusionsArea.setWrapStyleWord(true);
        JScrollPane inclusionsScroll = new JScrollPane(inclusionsArea);
        
        // Add components to GridBag layout
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(idLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0.4;
        detailsPanel.add(idField, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(nameLabel, gbc);
        
        gbc.gridx = 3; gbc.gridy = 0;
        gbc.gridwidth = 1; gbc.weightx = 0.4;
        detailsPanel.add(nameField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(statusLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0.4;
        detailsPanel.add(statusCombo, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(priceLabel, gbc);
        
        gbc.gridx = 3; gbc.gridy = 1;
        gbc.gridwidth = 1; gbc.weightx = 0.4;
        detailsPanel.add(priceField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(remarksLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        gbc.gridwidth = 3; gbc.weightx = 0.9;
        detailsPanel.add(remarksScroll, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 1; gbc.weightx = 0.1;
        detailsPanel.add(inclusionsLabel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        gbc.gridwidth = 3; gbc.weightx = 0.9;
        detailsPanel.add(inclusionsScroll, gbc);
        
        formPanel.add(detailsPanel, BorderLayout.CENTER);
        
        // Buttons Panel for form operations
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(240, 248, 255));
        
        JButton clearButton = createFormButton("Clear", new Color(158, 158, 158));
        JButton deleteButton = createFormButton("Delete", new Color(244, 67, 54));
        JButton updateButton = createFormButton("Update", new Color(33, 150, 243));
        JButton addButton = createFormButton("Add New", new Color(76, 175, 80));
        
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        
        formPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Add panels to split pane
        splitPane.setTopComponent(tablePanel);
        splitPane.setBottomComponent(formPanel);
        
        // Add components to main panel
        mainPanel.add(monitoringPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        mainPanel.add(splitPane);
        
        // Add main panel to scroll pane for vertical scrolling
        JScrollPane mainScrollPane = new JScrollPane(mainPanel);
        mainScrollPane.setBorder(null);
        mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        mainScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Add the scroll pane to the managemahcines panel
        managemahcines.setLayout(new BorderLayout());
        managemahcines.add(mainScrollPane, BorderLayout.CENTER);
        
        // Add action to table selection - populate form when row selected
        machineTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && machineTable.getSelectedRow() >= 0) {
                int row = machineTable.getSelectedRow();
                idField.setText(machineTable.getValueAt(row, 0).toString());
                nameField.setText(machineTable.getValueAt(row, 1).toString());
                statusCombo.setSelectedItem(machineTable.getValueAt(row, 2));
                priceField.setText(machineTable.getValueAt(row, 3).toString().replace("₱", ""));
                remarksArea.setText(machineTable.getValueAt(row, 4) != null ? 
                                  machineTable.getValueAt(row, 4).toString() : "");
                inclusionsArea.setText(machineTable.getValueAt(row, 5) != null ? 
                                     machineTable.getValueAt(row, 5).toString() : "");
                                     
                // Enable update/delete buttons
                updateButton.setEnabled(true);
                deleteButton.setEnabled(true);
                editTableButton.setEnabled(true);
                deleteTableButton.setEnabled(true);
                
                // Highlight the corresponding machine card in the monitor section
                highlightMachineInMonitor(Integer.parseInt(idField.getText()), machinesPanel);
            }
        });
        
        // Connect the table action buttons to form actions
        addTableButton.addActionListener(e -> {
            clearMachineForm(idField, nameField, statusCombo, priceField, remarksArea, inclusionsArea);
            nameField.requestFocus();
        });
        
        editTableButton.addActionListener(e -> {
            if (machineTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(managemahcines, "Please select a machine to edit", 
                                            "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            nameField.requestFocus();
        });
        
        deleteTableButton.addActionListener(e -> {
            if (machineTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(managemahcines, "Please select a machine to delete", 
                                            "No Selection", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Confirm deletion
            int confirmed = JOptionPane.showConfirmDialog(managemahcines, 
                           "Are you sure you want to delete this machine?\nThis action cannot be undone.", 
                           "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirmed == JOptionPane.YES_OPTION) {
                deleteMachine(Integer.parseInt(idField.getText()), machineModel);
                clearMachineForm(idField, nameField, statusCombo, priceField, remarksArea, inclusionsArea);
                
                // Refresh the monitor section
                loadMachinesToMonitor(machinesPanel);
            }
        });
        
        // Add action for Clear button
        clearButton.addActionListener(e -> {
            clearMachineForm(idField, nameField, statusCombo, priceField, remarksArea, inclusionsArea);
            machineTable.clearSelection();
            updateButton.setEnabled(false);
            deleteButton.setEnabled(false);
            editTableButton.setEnabled(false);
            deleteTableButton.setEnabled(false);
        });
        
        // Add action for Add button
        addButton.addActionListener(e -> {
            // Validate form
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(managemahcines, "Machine name is required", 
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double price;
            try {
                price = Double.parseDouble(priceField.getText().trim());
                if (price <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(managemahcines, "Please enter a valid price", 
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // If validation passes, add the machine
            addNewMachine(nameField.getText().trim(), 
                         statusCombo.getSelectedItem().toString(),
                         price,
                         remarksArea.getText().trim(),
                         inclusionsArea.getText().trim(),
                         machineModel);
            
            // Clear form after adding
            clearMachineForm(idField, nameField, statusCombo, priceField, remarksArea, inclusionsArea);
            
            // Refresh the monitor section
            loadMachinesToMonitor(machinesPanel);
        });
        
        // Add action for Update button
        updateButton.addActionListener(e -> {
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(managemahcines, "Please select a machine to update", 
                                            "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validate form same as in Add
            if (nameField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(managemahcines, "Machine name is required", 
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double price;
            try {
                price = Double.parseDouble(priceField.getText().trim().replace("₱", ""));
                if (price <= 0) throw new NumberFormatException();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(managemahcines, "Please enter a valid price", 
                                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Update the machine
            updateMachine(Integer.parseInt(idField.getText()),
                         nameField.getText().trim(), 
                         statusCombo.getSelectedItem().toString(),
                         price,
                         remarksArea.getText().trim(),
                         inclusionsArea.getText().trim(),
                         machineModel);
                         
            // Refresh the monitor section
            loadMachinesToMonitor(machinesPanel);
        });
        
        // Add action for Delete button
        deleteButton.addActionListener(e -> {
            if (idField.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(managemahcines, "Please select a machine to delete", 
                                            "Selection Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Confirm deletion
            int confirmed = JOptionPane.showConfirmDialog(managemahcines, 
                           "Are you sure you want to delete this machine?\nThis action cannot be undone.", 
                           "Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirmed == JOptionPane.YES_OPTION) {
                deleteMachine(Integer.parseInt(idField.getText()), machineModel);
                clearMachineForm(idField, nameField, statusCombo, priceField, remarksArea, inclusionsArea);
                
                // Refresh the monitor section
                loadMachinesToMonitor(machinesPanel);
            }
        });
        
        // Add search functionality
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { searchMachines(searchField.getText(), 
                                                      statusFilter.getSelectedItem().toString(), 
                                                      machineModel); }
            @Override
            public void removeUpdate(DocumentEvent e) { searchMachines(searchField.getText(), 
                                                      statusFilter.getSelectedItem().toString(), 
                                                      machineModel); }
            @Override
            public void changedUpdate(DocumentEvent e) { searchMachines(searchField.getText(), 
                                                       statusFilter.getSelectedItem().toString(), 
                                                       machineModel); }
        });
        
        // Add status filter action
        statusFilter.addActionListener(e -> searchMachines(searchField.getText(), 
                                                       statusFilter.getSelectedItem().toString(), 
                                                       machineModel));
        
        // Configure row coloring based on status
        machineTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    String status = (String) table.getValueAt(row, 2);
                    switch (status) {
                        case "Available":
                            c.setBackground(new Color(237, 247, 237));
                            break;
                        case "Reserved":
                            c.setBackground(new Color(255, 248, 225)); 
                            break;
                        case "Rented":
                            c.setBackground(new Color(227, 242, 253));
                            break;
                        case "Not Available":
                            c.setBackground(new Color(253, 237, 237));
                            break;
                        default:
                            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                    }
                }
                
                // Format price column
                if (column == 3 && value != null) {
                    try {
                        double price = Double.parseDouble(value.toString().replace("₱", "").trim());
                        setText(String.format("₱%.2f", price));
                    } catch (NumberFormatException ex) {
                        // Just display as is if it's not a valid number
                        setText(value.toString());
                    }
                }
                
                return c;
            }
        });
        
        // Initially disable update/delete buttons
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);
        editTableButton.setEnabled(false);
        deleteTableButton.setEnabled(false);
        
        // Load initial data
        loadMachinesData(machineModel);
        
        // Set the divider location after everything is loaded
        SwingUtilities.invokeLater(() -> splitPane.setDividerLocation(280));
    }
    
    /**
     * Highlights a machine card in the monitor section
     */
    private void highlightMachineInMonitor(int machineId, JPanel machinesPanel) {
        // Find and highlight the matching machine card
        for (Component component : machinesPanel.getComponents()) {
            if (component instanceof JPanel) {
                JPanel card = (JPanel) component;
                // Get the machine ID from the card (stored as a client property)
                Object cardMachineIdObj = card.getClientProperty("machineId");
                if (cardMachineIdObj != null && (int)cardMachineIdObj == machineId) {
                    // Highlight this card
                    for (Component c : machinesPanel.getComponents()) {
                        if (c instanceof JPanel) {
                            ((JComponent) c).setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                                BorderFactory.createEmptyBorder(10, 10, 10, 10)
                            ));
                        }
                    }
                    card.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(33, 150, 243), 3, true),
                        BorderFactory.createEmptyBorder(8, 8, 8, 8)
                    ));
                    return;
                }
            }
        }
    }

    
    
    /**
     * Creates a styled button for the form
     */
    private JButton createFormButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
    
    /**
     * Load all machines data into the table
     */
    private void loadMachinesData(DefaultTableModel model) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            model.setRowCount(0); // Clear existing data
            
            String query = "SELECT machine_id, machine_name, status, price, remarks, inclusion FROM videoke_machines ORDER BY machine_id";
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("machine_id"),
                    rs.getString("machine_name"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getString("remarks"),
                    rs.getString("inclusion")
                });
            }
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
    
    /**
     * Search machines based on text and status filter
     */
    private void searchMachines(String searchText, String statusFilter, DefaultTableModel model) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            model.setRowCount(0); // Clear existing data
            
            StringBuilder queryBuilder = new StringBuilder(
                "SELECT machine_id, machine_name, status, price, remarks, inclusion FROM videoke_machines WHERE 1=1"
            );
            
            // Add search condition if search text is not empty
            if (searchText != null && !searchText.trim().isEmpty()) {
                queryBuilder.append(" AND (machine_name LIKE ? OR remarks LIKE ? OR inclusion LIKE ?)");
            }
            
            // Add status filter if not "All"
            if (!"All".equals(statusFilter)) {
                queryBuilder.append(" AND status = ?");
            }
            
            queryBuilder.append(" ORDER BY machine_id");
            
            pstmt = connection.prepareStatement(queryBuilder.toString());
            
            int paramIndex = 1;
            
            // Set search parameters
            if (searchText != null && !searchText.trim().isEmpty()) {
                String wildcardSearch = "%" + searchText + "%";
                pstmt.setString(paramIndex++, wildcardSearch);
                pstmt.setString(paramIndex++, wildcardSearch);
                pstmt.setString(paramIndex++, wildcardSearch);
            }
            
            // Set status parameter
            if (!"All".equals(statusFilter)) {
                pstmt.setString(paramIndex, statusFilter);
            }
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("machine_id"),
                    rs.getString("machine_name"),
                    rs.getString("status"),
                    rs.getDouble("price"),
                    rs.getString("remarks"),
                    rs.getString("inclusion")
                });
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
    
    /**
     * Clear all form fields
     */
    private void clearMachineForm(JTextField idField, JTextField nameField, JComboBox<String> statusCombo,
                                 JTextField priceField, JTextArea remarksArea, JTextArea inclusionsArea) {
        idField.setText("");
        nameField.setText("");
        statusCombo.setSelectedIndex(0);
        priceField.setText("");
        remarksArea.setText("");
        inclusionsArea.setText("");
        nameField.requestFocus();
    }
    
    /**
     * Add a new machine to the database
     */
    private void addNewMachine(String name, String status, double price, 
                              String remarks, String inclusions, DefaultTableModel model) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            String query = "INSERT INTO videoke_machines (machine_name, status, price, remarks, inclusion) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, status);
            pstmt.setDouble(3, price);
            pstmt.setString(4, remarks);
            pstmt.setString(5, inclusions);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Machine added successfully!", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMachinesData(model);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding machine: " + e.getMessage(), 
                                         "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update an existing machine
     */
    private void updateMachine(int machineId, String name, String status, double price, 
                              String remarks, String inclusions, DefaultTableModel model) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            String query = "UPDATE videoke_machines SET machine_name = ?, status = ?, price = ?, remarks = ?, inclusion = ? WHERE machine_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, status);
            pstmt.setDouble(3, price);
            pstmt.setString(4, remarks);
            pstmt.setString(5, inclusions);
            pstmt.setInt(6, machineId);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Machine updated successfully!", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMachinesData(model);
            } else {
                JOptionPane.showMessageDialog(null, "No changes made or machine not found.", 
                                             "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating machine: " + e.getMessage(), 
                                         "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Delete a machine from the database
     */
    private void deleteMachine(int machineId, DefaultTableModel model) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            String query = "DELETE FROM videoke_machines WHERE machine_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, machineId);
            
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Machine deleted successfully!", 
                                             "Success", JOptionPane.INFORMATION_MESSAGE);
                loadMachinesData(model);
            } else {
                JOptionPane.showMessageDialog(null, "No machine found with ID: " + machineId, 
                                             "Information", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting machine: " + e.getMessage(), 
                                         "Database Error", JOptionPane.ERROR_MESSAGE);
        }
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
        
        // Create search panel with search field AND status filter
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        searchPanel.setBackground(new Color(240, 248, 255));
        
        JLabel searchLabel = new JLabel("Search:");
        searchLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        
        JTextField searchField = new JTextField(15);
        searchField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        
        // Add status filter dropdown
        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        
        JComboBox<String> statusFilter = new JComboBox<>(new String[] {
            "All", "Available", "Reserved", "Rented", "Not Available"
        });
        statusFilter.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        statusFilter.setPreferredSize(new Dimension(130, 25));
        statusFilter.setBackground(Color.WHITE);
        
        // Small refresh button
        JButton refreshButton = new JButton("↻");
        refreshButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        refreshButton.setFocusPainted(false);
        refreshButton.setToolTipText("Refresh machine list");
        refreshButton.addActionListener(e -> loadMachineTableData());
        
        // Add filtering functionality (combined search & status filter)
        DocumentListener searchListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { 
                applyFilters(searchField.getText(), (String)statusFilter.getSelectedItem()); 
            }
            @Override
            public void removeUpdate(DocumentEvent e) { 
                applyFilters(searchField.getText(), (String)statusFilter.getSelectedItem()); 
            }
            @Override
            public void changedUpdate(DocumentEvent e) { 
                applyFilters(searchField.getText(), (String)statusFilter.getSelectedItem()); 
            }
        };
        
        searchField.getDocument().addDocumentListener(searchListener);
        statusFilter.addActionListener(e -> applyFilters(searchField.getText(), (String)statusFilter.getSelectedItem()));
        
        // Add components to search panel
        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(statusLabel);
        searchPanel.add(statusFilter);
        searchPanel.add(Box.createHorizontalStrut(10));
        searchPanel.add(refreshButton);
        
        // Add search panel and table to table panel
        tablePanel.add(searchPanel, BorderLayout.NORTH);
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Rest of your method remains unchanged
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
     * Apply both text search and status filters to the machine table
     * @param searchText Text to search for
     * @param statusFilter Status filter to apply
     */
    private void applyFilters(String searchText, String statusFilter) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    
        try {
            JTable machineTable = findMachineTable();
            if (machineTable == null) return;
            
            DefaultTableModel model = (DefaultTableModel) machineTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            StringBuilder queryBuilder = new StringBuilder(
                "SELECT machine_id, machine_name, status, price, remarks FROM videoke_machines WHERE 1=1"
            );
            
            // Add search condition if search text is not empty
            if (searchText != null && !searchText.trim().isEmpty()) {
                queryBuilder.append(" AND (machine_name LIKE ? OR remarks LIKE ?)");
            }
            
            // Add status filter if not "All"
            if (!"All".equals(statusFilter)) {
                queryBuilder.append(" AND status = ?");
            }
            
            queryBuilder.append(" ORDER BY machine_id");
            
            pstmt = connection.prepareStatement(queryBuilder.toString());
            
            int paramIndex = 1;
            
            // Set search parameters
            if (searchText != null && !searchText.trim().isEmpty()) {
                String wildcardSearch = "%" + searchText + "%";
                pstmt.setString(paramIndex++, wildcardSearch);
                pstmt.setString(paramIndex++, wildcardSearch);
            }
            
            // Set status parameter
            if (!"All".equals(statusFilter)) {
                pstmt.setString(paramIndex, statusFilter);
            }
            
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
            
            // Apply row coloring based on status
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
                        case "Not Available":
                            c.setBackground(new Color(240, 240, 240));
                            break;
                        default:
                            c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
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
            JOptionPane.showMessageDialog(null, "Error filtering machine data: " + e.getMessage(), 
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
    
    // Replace the existing filterMachineTable method with the applyFilters method
    private void filterMachineTable(String searchText) {
        if (searchText == null || searchText.trim().isEmpty()) {
            // If search is empty, just load all data
            loadMachineTableData();
            return;
        }
        
        Connection connection = DBKonek.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        PreparedStatement pstmt = null;
        ResultSet rs = null;
    
        try {
            JTable machineTable = findMachineTable();
            if (machineTable == null) return;
            
            DefaultTableModel model = (DefaultTableModel) machineTable.getModel();
            model.setRowCount(0); // Clear existing data
            
            String query = "SELECT machine_id, machine_name, status, price, remarks FROM videoke_machines " +
                          "WHERE machine_name LIKE ? OR status LIKE ? OR remarks LIKE ? " +
                          "ORDER BY machine_id";
                          
            pstmt = connection.prepareStatement(query);
            String searchPattern = "%" + searchText + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
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
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error searching machine data: " + e.getMessage(), 
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

    private void initCustomerManagementPanel() {
        // Clear existing components
        jPanel11.removeAll();
        
        // Set modern layout with proper padding
        jPanel11.setLayout(new BorderLayout(0, 10));
        jPanel11.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        jPanel11.setBackground(new Color(240, 248, 255));
        
        // Create stylish header panel
        JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
        headerPanel.setOpaque(false);
        
        JLabel titleLabel = new JLabel("Customer Management");
        titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        titleLabel.setForeground(new Color(33, 33, 33));
        headerPanel.add(titleLabel, BorderLayout.WEST);
        
        // Create search panel with modern styling
        JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
        searchPanel.setPreferredSize(new Dimension(300, 32));
        searchPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(2, 8, 2, 8)
        ));
        searchPanel.setBackground(Color.WHITE);
        
        JLabel searchIcon = new JLabel("🔍");
        searchIcon.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        searchIcon.setForeground(new Color(150, 150, 150));
        
        JTextField searchField = new JTextField();
        searchField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        searchField.setBorder(null);
        searchField.setOpaque(false);
        searchField.setToolTipText("Search by name, contact, email or address");
        
        searchPanel.add(searchIcon, BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        
        headerPanel.add(searchPanel, BorderLayout.EAST);
        jPanel11.add(headerPanel, BorderLayout.NORTH);
        
        // Set up table model with proper column names
        DefaultTableModel model = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID", "Customer Name", "Contact Number", "Email", "Address", "Rentals"}
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 0 || columnIndex == 5) return Integer.class;
                return String.class;
            }
        };
        
        jTable1.setModel(model);
        jTable1.setRowHeight(28);
        jTable1.setShowGrid(true);
        jTable1.setGridColor(new Color(240, 240, 240));
        jTable1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        jTable1.getTableHeader().setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        jTable1.getTableHeader().setOpaque(false);
        jTable1.getTableHeader().setBackground(new Color(245, 245, 245));
        jTable1.setSelectionBackground(new Color(225, 240, 255));
        jTable1.setSelectionForeground(Color.BLACK);
        jTable1.setRowMargin(3);
        
        // Set column widths
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID
        jTable1.getColumnModel().getColumn(1).setPreferredWidth(180); // Name
        jTable1.getColumnModel().getColumn(2).setPreferredWidth(120); // Contact
        jTable1.getColumnModel().getColumn(3).setPreferredWidth(160); // Email
        jTable1.getColumnModel().getColumn(4).setPreferredWidth(200); // Address
        jTable1.getColumnModel().getColumn(5).setPreferredWidth(70);  // Rentals
        
        // Create scrollpane for the table with better styling
        JScrollPane scrollPane = new JScrollPane(jTable1);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        scrollPane.setViewportBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        jPanel11.add(scrollPane, BorderLayout.CENTER);
        
        // Create actions panel at the bottom with modern buttons
        JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionsPanel.setOpaque(false);
        
        JButton addButton = createStyledButton("Add Customer", new Color(76, 175, 80));
        JButton editButton = createStyledButton("Edit", new Color(33, 150, 243));
        JButton deleteButton = createStyledButton("Delete", new Color(244, 67, 54));
        JButton historyButton = createStyledButton("View Rental History", new Color(156, 39, 176));
        JButton refreshButton = createStyledButton("Refresh", new Color(158, 158, 158));
        
        addButton.setIcon(new ImageIcon(getClass().getResource("/customermngmnt.png")));
        
        // Add button actions
        addButton.addActionListener(e -> showCustomerDialog(null));
        editButton.addActionListener(e -> editSelectedCustomer());
        deleteButton.addActionListener(e -> deleteSelectedCustomer());
        historyButton.addActionListener(e -> showCustomerRentalHistory());
        refreshButton.addActionListener(e -> loadCustomerData());
        
        actionsPanel.add(addButton);
        actionsPanel.add(editButton);
        actionsPanel.add(historyButton);
        actionsPanel.add(deleteButton);
        actionsPanel.add(refreshButton);
        
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        
        // Add info label on the left side showing record count
        JLabel countLabel = new JLabel("0 customers found");
        countLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 12));
        countLabel.setForeground(new Color(100, 100, 100));
        
        // Update the count label when data is loaded
        model.addTableModelListener(e -> {
            int count = model.getRowCount();
            countLabel.setText(count + (count == 1 ? " customer found" : " customers found"));
        });
        
        bottomPanel.add(countLabel, BorderLayout.WEST);
        bottomPanel.add(actionsPanel, BorderLayout.EAST);
        
        jPanel11.add(bottomPanel, BorderLayout.SOUTH);
        
        // Add search functionality
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { searchCustomers(searchField.getText()); }
            @Override
            public void removeUpdate(DocumentEvent e) { searchCustomers(searchField.getText()); }
            @Override
            public void changedUpdate(DocumentEvent e) { searchCustomers(searchField.getText()); }
        });
        
        // Add double-click listener to table for quick editing
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    editSelectedCustomer();
                }
            }
        });
        
        // Load customer data initially
        loadCustomerData();
        
        // Apply modern row striping for better readability
        jTable1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                    
                Component c = super.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                }
                
                return c;
            }
        });
    }

    private void loadCustomerData() {
        Connection connection = DBKonek.getConnection();
        if (connection == null) {
            JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data
            
            // Query to get distinct customers from rental_logs and count their rentals
            String query = "SELECT MIN(log_id) as id, customer_name, contact_number, email, address, " +
                         "COUNT(*) as rental_count " +
                         "FROM rental_logs " +
                         "GROUP BY customer_name, contact_number " +
                         "ORDER BY customer_name";
                          
            pstmt = connection.prepareStatement(query);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("contact_number"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getInt("rental_count")
                });
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading customer data: " + e.getMessage(), 
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

    private void searchCustomers(String query) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        try {
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0); // Clear existing data
            
            if (query == null || query.trim().isEmpty()) {
                // If search is empty, load all data
                loadCustomerData();
                return;
            }
            
            // Search query with wildcards directly from rental_logs
            String searchQuery = "SELECT MIN(log_id) as id, customer_name, contact_number, email, address, " +
                         "COUNT(*) as rental_count " +
                         "FROM rental_logs " +
                         "WHERE customer_name LIKE ? OR contact_number LIKE ? " +
                         "OR email LIKE ? OR address LIKE ? " +
                         "GROUP BY customer_name, contact_number " +
                         "ORDER BY customer_name";
                           
            pstmt = connection.prepareStatement(searchQuery);
            String wildcardSearch = "%" + query + "%";
            pstmt.setString(1, wildcardSearch);
            pstmt.setString(2, wildcardSearch);
            pstmt.setString(3, wildcardSearch);
            pstmt.setString(4, wildcardSearch);
            
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id"),
                    rs.getString("customer_name"),
                    rs.getString("contact_number"),
                    rs.getString("email"),
                    rs.getString("address"),
                    rs.getInt("rental_count")
                });
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


    private void showCustomerDialog(Object customerIdObj) {
        // Create dialog with improved styling
        JDialog dialog = new JDialog(this, customerIdObj == null ? "Add New Customer" : "Edit Customer", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 320);
        dialog.setLocationRelativeTo(this);
        dialog.setResizable(false);
        
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        mainPanel.setBackground(Color.WHITE);
        
        // Form panel with GridBagLayout for better control
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        // Create styled components
        JLabel nameLabel = new JLabel("Customer Name:");
        nameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JLabel emailLabel = new JLabel("Email Address:");
        emailLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        
        JTextField nameField = new JTextField(20);
        nameField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        nameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JTextField contactField = new JTextField(20);
        contactField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        contactField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JTextField emailField = new JTextField(20);
        emailField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        emailField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        JTextField addressField = new JTextField(20);
        addressField.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        addressField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(204, 204, 204)),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        
        // Add components to form with GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        formPanel.add(nameLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        formPanel.add(nameField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        formPanel.add(contactLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        formPanel.add(contactField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        formPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        formPanel.add(emailField, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        formPanel.add(addressLabel, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        formPanel.add(addressField, gbc);
        
        // Button panel with FlowLayout
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton saveButton = new JButton(customerIdObj == null ? "Add" : "Save");
        saveButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        cancelButton.setBackground(new Color(240, 240, 240));
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        // If editing, populate fields with customer data from rental_logs
        String originalName = "";
        String originalContact = "";
        if (customerIdObj != null) {
            Integer logId = (Integer) customerIdObj;
            Connection connection = DBKonek.getConnection();
            if (connection != null) {
                try {
                    PreparedStatement pstmt = connection.prepareStatement(
                        "SELECT customer_name, contact_number, email, address FROM rental_logs WHERE log_id = ?"
                    );
                    pstmt.setInt(1, logId);
                    ResultSet rs = pstmt.executeQuery();
                    
                    if (rs.next()) {
                        originalName = rs.getString("customer_name");
                        originalContact = rs.getString("contact_number");
                        
                        nameField.setText(originalName);
                        contactField.setText(originalContact);
                        emailField.setText(rs.getString("email"));
                        addressField.setText(rs.getString("address"));
                    }
                    
                    rs.close();
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        
        // Add action listeners
        final String finalOriginalName = originalName;
        final String finalOriginalContact = originalContact;
        
        saveButton.addActionListener(e -> {
            // Validate inputs
            String name = nameField.getText().trim();
            String contact = contactField.getText().trim();
            String email = emailField.getText().trim();
            String address = addressField.getText().trim();
            
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, 
                    "Customer name is required", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Save to database - for editing, we need to update all entries with the same customer name and contact
            if (customerIdObj != null) {
                // When editing, pass the original customer name and contact for updating all relevant records
                saveCustomer(null, name, contact, email, address, finalOriginalName, finalOriginalContact);
            } else {
                // For new customers, we only have the new information
                saveCustomer(null, name, contact, email, address, null, null);
            }
            
            dialog.dispose();
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        // Add panels to main layout
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        dialog.add(mainPanel);
        
        // Show dialog
        dialog.setVisible(true);
    }

    private void saveCustomer(Integer logId, String name, String contact, String email, 
                             String address, String originalName, String originalContact) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            if (originalName != null && originalContact != null) {
                // This is an update - update all entries where customer_name and contact_number match
                PreparedStatement pstmt = connection.prepareStatement(
                    "UPDATE rental_logs SET customer_name = ?, contact_number = ?, email = ?, address = ? " +
                    "WHERE customer_name = ? AND contact_number = ?"
                );
                pstmt.setString(1, name);
                pstmt.setString(2, contact);
                pstmt.setString(3, email);
                pstmt.setString(4, address);
                pstmt.setString(5, originalName);
                pstmt.setString(6, originalContact);
                
                int rowsUpdated = pstmt.executeUpdate();
                pstmt.close();
                
                // Show success message
                JOptionPane.showMessageDialog(this, 
                    rowsUpdated + " rental records updated for customer: " + name, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                // This is a new customer - insert a dummy rental entry
                PreparedStatement pstmt = connection.prepareStatement(
                    "INSERT INTO rental_logs (customer_name, contact_number, email, address, machine_id, " +
                    "machine_name, status, rental_start_date) VALUES (?, ?, ?, ?, 0, 'New Customer', 'N/A', NOW())"
                );
                pstmt.setString(1, name);
                pstmt.setString(2, contact);
                pstmt.setString(3, email);
                pstmt.setString(4, address);
                
                pstmt.executeUpdate();
                pstmt.close();
                
                // Show success message
                JOptionPane.showMessageDialog(this, 
                    "New customer added successfully: " + name, 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Reload customer data
            loadCustomerData();
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                "Error saving customer: " + e.getMessage(), 
                "Database Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
 * Add a machine monitoring dashboard to the Dashboard panel
 */
private void addMachineMonitoringSection() {
    // Create a scrollable panel for machine monitoring
    JPanel monitoringPanel = new JPanel();
    monitoringPanel.setLayout(new BoxLayout(monitoringPanel, BoxLayout.Y_AXIS));
    monitoringPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    monitoringPanel.setBackground(new Color(240, 248, 255));
    
    // Add title
    JLabel titleLabel = new JLabel("Machine Status Monitor");
    titleLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 18));
    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    monitoringPanel.add(titleLabel);
    monitoringPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    
    // Create a panel for machines
    JPanel machinesPanel = new JPanel(new GridLayout(0, 3, 20, 20));
    machinesPanel.setBackground(new Color(240, 248, 255));
    
    // Create scroll pane for machines
    JScrollPane scrollPane = new JScrollPane(machinesPanel);
    scrollPane.setBorder(null);
    scrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    
    // Load machines from database to fill the grid
    loadMachinesToMonitor(machinesPanel);
    
    // Add refresh button
    JButton refreshButton = new JButton("Refresh");
    refreshButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
    refreshButton.setBackground(new Color(33, 150, 243));
    refreshButton.setForeground(Color.WHITE);
    refreshButton.setFocusPainted(false);
    refreshButton.addActionListener(e -> loadMachinesToMonitor(machinesPanel));
    refreshButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    
    monitoringPanel.add(scrollPane);
    monitoringPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    monitoringPanel.add(refreshButton);
    
    // Create a scroll pane for the entire monitoring panel
    JScrollPane mainScrollPane = new JScrollPane(monitoringPanel);
    mainScrollPane.setBorder(null);
    mainScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    mainScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // Add to the dashboard panel (jPanel9)
    dashboardpanel.removeAll();
    dashboardpanel.setLayout(new BorderLayout());
    dashboardpanel.add(mainScrollPane, BorderLayout.CENTER);
    dashboardpanel.revalidate();
    dashboardpanel.repaint();
}

/**
 * Load all machines for monitoring
 */
/**
 * Load all machines for monitoring
 */
private void loadMachinesToMonitor(JPanel machinesPanel) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    machinesPanel.removeAll();
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        String query = "SELECT machine_id, machine_name, status, price, remarks, inclusion FROM videoke_machines ORDER BY machine_id";
        pstmt = connection.prepareStatement(query);
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            int machineId = rs.getInt("machine_id");
            String machineName = rs.getString("machine_name");
            String status = rs.getString("status");
            double price = rs.getDouble("price");
            String remarks = rs.getString("remarks");
            
            // Create card for each machine
            JPanel machineCard = createMachineCard(machineId, machineName, status, price, remarks);
            // Store the machine ID as a client property for later reference
            machineCard.putClientProperty("machineId", machineId);
            machinesPanel.add(machineCard);
        }
        
        // Add some empty cards if there are few machines
        if (machinesPanel.getComponentCount() < 3) {
            int emptyCards = 3 - machinesPanel.getComponentCount();
            for (int i = 0; i < emptyCards; i++) {
                JPanel emptyCard = new JPanel();
                emptyCard.setBackground(new Color(240, 248, 255));
                machinesPanel.add(emptyCard);
            }
        }
        
        machinesPanel.revalidate();
        machinesPanel.repaint();
        
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

/**
 * Create a card displaying machine status and info
 */
private JPanel createMachineCard(int machineId, String machineName, String status, double price, String remarks) {
    // Create panel with border
    JPanel card = new JPanel(new BorderLayout(10, 5));
    card.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
        BorderFactory.createEmptyBorder(10, 10, 10, 10)
    ));
    
    // Set background color based on status
    Color bgColor;
    switch (status) {
        case "Available":
            bgColor = new Color(232, 245, 233); // Light green
            break;
        case "Reserved":
            bgColor = new Color(255, 243, 224); // Light orange
            break;
        case "Rented":
            bgColor = new Color(227, 242, 253); // Light blue
            break;
        case "Not Available":
            bgColor = new Color(248, 230, 230); // Light red
            break;
        default:
            bgColor = new Color(248, 248, 248); // Light gray
    }
    card.setBackground(bgColor);
    
    // Create header panel with machine name and status badge
    JPanel headerPanel = new JPanel(new BorderLayout(5, 0));
    headerPanel.setOpaque(false);
    
    JLabel idLabel = new JLabel("#" + machineId);
    idLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
    
    JLabel nameLabel = new JLabel(machineName);
    nameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    
    JPanel idNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
    idNamePanel.setOpaque(false);
    idNamePanel.add(idLabel);
    idNamePanel.add(nameLabel);
    
    JLabel statusLabel = new JLabel(status);
    statusLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 11));
    statusLabel.setForeground(Color.WHITE);
    statusLabel.setBorder(BorderFactory.createEmptyBorder(3, 8, 3, 8));
    
    // Set status badge color
    Color statusColor;
    switch (status) {
        case "Available":
            statusColor = new Color(76, 175, 80); // Green
            break;
        case "Reserved":
            statusColor = new Color(255, 152, 0); // Orange
            break;
        case "Rented":
            statusColor = new Color(33, 150, 243); // Blue
            break;
        case "Not Available":
            statusColor = new Color(244, 67, 54); // Red
            break;
        default:
            statusColor = new Color(117, 117, 117); // Gray
    }
    
    statusLabel.setOpaque(true);
    statusLabel.setBackground(statusColor);
    
    headerPanel.add(idNamePanel, BorderLayout.WEST);
    headerPanel.add(statusLabel, BorderLayout.EAST);
    
    // Add info labels
    JPanel infoPanel = new JPanel(new GridLayout(0, 1, 0, 5));
    infoPanel.setOpaque(false);
    
    JLabel priceLabel = new JLabel("Price: ₱" + String.format("%.2f", price) + " / day");
    priceLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
    
    JLabel remarksLabel = new JLabel("<html><b>Remarks:</b> " + 
                                    (remarks != null && !remarks.isEmpty() ? remarks : "N/A") + "</html>");
    remarksLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
    
    infoPanel.add(priceLabel);
    infoPanel.add(remarksLabel);
    
    // Create a details button at the bottom
    JButton detailsButton = new JButton("View Details");
    detailsButton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
    detailsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
    detailsButton.setFocusPainted(false);
    detailsButton.setMargin(new Insets(2, 8, 2, 8));
    detailsButton.addActionListener(e -> {
        // Switch to Manage Machines tab and select this machine
        jTabbedPane1.setSelectedIndex(3); // Index for Manage Machines tab
        // Need to implement selection of this machine in the table
        highlightMachineInTable(machineId);
    });
    
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    buttonPanel.setOpaque(false);
    buttonPanel.add(detailsButton);
    
    // Add all components to card
    card.add(headerPanel, BorderLayout.NORTH);
    card.add(infoPanel, BorderLayout.CENTER);
    card.add(buttonPanel, BorderLayout.SOUTH);
    
    return card;
}

/**
 * Highlight and select a machine in the management table
 */
private void highlightMachineInTable(int machineId) {
    // This method would need to find the machine in the table by ID
    // and select the corresponding row
    // This requires the machine management table to be accessible here
}

    private void editSelectedCustomer() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to edit", 
                                         "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int logId = (int) jTable1.getValueAt(selectedRow, 0);
        showCustomerDialog(logId);
    }

    private void deleteSelectedCustomer() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to delete", 
                                         "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String customerName = (String) jTable1.getValueAt(selectedRow, 1);
        String contactNumber = (String) jTable1.getValueAt(selectedRow, 2);
        int rentalCount = (int) jTable1.getValueAt(selectedRow, 5);
        
        // For rental_logs table, we warn that this is a destructive operation
        String message = "<html><b>Warning: This will delete " + rentalCount + " rental records for this customer.</b>" +
                        "<br><br>Are you sure you want to delete all rental records for:" +
                        "<br>Name: " + customerName + 
                        "<br>Contact: " + contactNumber + 
                        "<br><br><font color='red'><b>This action cannot be undone!</b></font></html>";
        
        int confirm = JOptionPane.showConfirmDialog(
            this,
            message,
            "Confirm Deletion",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
        );
        
        if (confirm == JOptionPane.YES_OPTION) {
            Connection connection = DBKonek.getConnection();
            if (connection == null) return;
            
            try {
                // Delete all records for this customer
                PreparedStatement pstmt = connection.prepareStatement(
                    "DELETE FROM rental_logs WHERE customer_name = ? AND contact_number = ?"
                );
                pstmt.setString(1, customerName);
                pstmt.setString(2, contactNumber);
                int rowsAffected = pstmt.executeUpdate();
                pstmt.close();
                
                if (rowsAffected > 0) {
                    // Reload customer data
                    loadCustomerData();
                    
                    JOptionPane.showMessageDialog(
                        this, 
                        rowsAffected + " rental records deleted for customer: " + customerName, 
                        "Success", 
                        JOptionPane.INFORMATION_MESSAGE
                    );
                } else {
                    JOptionPane.showMessageDialog(
                        this, 
                        "No records found for deletion.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                    );
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(
                    this, 
                    "Error deleting customer records: " + e.getMessage(), 
                    "Database Error", 
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void showCustomerRentalHistory() {
        int selectedRow = jTable1.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a customer to view history", 
                                         "No Selection", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String customerName = (String) jTable1.getValueAt(selectedRow, 1);
        String contactNumber = (String) jTable1.getValueAt(selectedRow, 2);
        
        // Create a modern styled dialog
        JDialog historyDialog = new JDialog(this, "Rental History - " + customerName, true);
        historyDialog.setSize(900, 550);
        historyDialog.setLocationRelativeTo(this);
        historyDialog.setLayout(new BorderLayout());
        
        // Add customer info panel at top
        JPanel customerInfoPanel = new JPanel();
        customerInfoPanel.setBackground(new Color(245, 245, 250));
        customerInfoPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        customerInfoPanel.setLayout(new GridLayout(1, 3, 10, 0));
        
        // Use consistent font: Segoe UI, PLAIN, 12
        java.awt.Font regularFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
        java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
        java.awt.Font headerFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
        
        JLabel nameLabel = new JLabel("<html><b>Customer:</b> " + customerName + "</html>");
        nameLabel.setFont(regularFont);
        
        JLabel contactLabel = new JLabel("<html><b>Contact:</b> " + contactNumber + "</html>");
        contactLabel.setFont(regularFont);
        
        customerInfoPanel.add(nameLabel);
        customerInfoPanel.add(contactLabel);
        customerInfoPanel.add(new JLabel()); // Empty space
        
        // Create table for rental history with modern styling and horizontal scrolling support
        String[] columns = {"ID", "Machine", "Rental Date", "Return Date", "Status", "Amount Paid", "Payment Type", "Remarks"};
        DefaultTableModel historyModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        JTable historyTable = new JTable(historyModel);
        historyTable.setRowHeight(28);
        historyTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scrolling
        historyTable.setShowGrid(true);
        historyTable.setGridColor(new Color(240, 240, 240));
        historyTable.setFont(regularFont);
        historyTable.getTableHeader().setFont(boldFont);
        historyTable.setSelectionBackground(new Color(225, 240, 255));
        historyTable.setSelectionForeground(Color.BLACK);
        
        // Set column sizes
        historyTable.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
        historyTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Machine
        historyTable.getColumnModel().getColumn(2).setPreferredWidth(110);  // Rental Date
        historyTable.getColumnModel().getColumn(3).setPreferredWidth(110);  // Return Date
        historyTable.getColumnModel().getColumn(4).setPreferredWidth(100);  // Status
        historyTable.getColumnModel().getColumn(5).setPreferredWidth(100);  // Amount
        historyTable.getColumnModel().getColumn(6).setPreferredWidth(100);  // Payment
        historyTable.getColumnModel().getColumn(7).setPreferredWidth(180);  // Remarks
        
        // Load rental history data directly from rental_logs
        Connection connection = DBKonek.getConnection();
        if (connection != null) {
            try {
                PreparedStatement pstmt = connection.prepareStatement(
                    "SELECT r.log_id, r.machine_name, r.rental_start_date, r.rental_end_date, " +
                    "r.Status, p.amount_paid, p.payment_type, r.additional_item " +
                    "FROM rental_logs r " +
                    "LEFT JOIN payments p ON r.log_id = p.log_id " +
                    "WHERE r.customer_name = ? AND r.contact_number = ? " +
                    "AND r.machine_id > 0 " + // Exclude dummy entries
                    "ORDER BY r.rental_start_date DESC"
                );
                pstmt.setString(1, customerName);
                pstmt.setString(2, contactNumber);
                ResultSet rs = pstmt.executeQuery();
                
                while (rs.next()) {
                    historyModel.addRow(new Object[] {
                        rs.getInt("log_id"),
                        rs.getString("machine_name"),
                        rs.getDate("rental_start_date"),
                        rs.getDate("rental_end_date"),
                        rs.getString("Status"),
                        String.format("₱%.2f", rs.getDouble("amount_paid")),
                        rs.getString("payment_type"),
                        rs.getString("additional_item")
                    });
                }
                
                rs.close();
                pstmt.close();
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        // Add row coloring based on status
        historyTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                if (!isSelected) {
                    String status = (String) table.getValueAt(row, 4);
                    if ("Completed".equalsIgnoreCase(status)) {
                        c.setBackground(new Color(232, 245, 233));
                    } else if ("Pickup".equalsIgnoreCase(status)) {
                        c.setBackground(new Color(255, 243, 224));
                    } else if ("Rented".equalsIgnoreCase(status)) {
                        c.setBackground(new Color(227, 242, 253));
                    } else if ("Overdue".equalsIgnoreCase(status)) {
                        c.setBackground(new Color(253, 237, 237));
                    } else {
                        c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                    }
                }
                
                c.setFont(regularFont); // Ensure consistent font in cells
                return c;
            }
        });
        
        // Add scroll pane with horizontal and vertical scrollbars
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 15));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        // Add footer with buttons
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        footerPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 15, 15));
        
        JButton updateStatusButton = new JButton("Update Status");
        updateStatusButton.setFont(boldFont);
        updateStatusButton.setBackground(new Color(255, 152, 0));
        updateStatusButton.setForeground(Color.WHITE);
        updateStatusButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        updateStatusButton.setFocusPainted(false);
        
        JButton printButton = new JButton("Print History");
        printButton.setFont(boldFont);
        printButton.setBackground(new Color(33, 150, 243));
        printButton.setForeground(Color.WHITE);
        printButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        printButton.setFocusPainted(false);
        
        JButton closeButton = new JButton("Close");
        closeButton.setFont(boldFont);
        closeButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        closeButton.setFocusPainted(false);
        
        footerPanel.add(updateStatusButton);
        footerPanel.add(printButton);
        footerPanel.add(closeButton);
        
        // Add action listeners
        updateStatusButton.addActionListener(e -> {
            int selectedHistoryRow = historyTable.getSelectedRow();
            if (selectedHistoryRow == -1) {
                JOptionPane.showMessageDialog(historyDialog, 
                    "Please select a rental to update", 
                    "No Selection", 
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            int logId = (int) historyTable.getValueAt(selectedHistoryRow, 0);
            String currentStatus = (String) historyTable.getValueAt(selectedHistoryRow, 4);
            
            // Create status selection dialog
            String[] statuses = {"Pickup", "Completed", "Cancelled"};
            JComboBox<String> statusCombo = new JComboBox<>(statuses);
            statusCombo.setFont(regularFont);
            statusCombo.setSelectedItem(currentStatus);
            
            JPanel statusPanel = new JPanel(new BorderLayout(10, 10));
            statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            
            JLabel promptLabel = new JLabel("Select new status:");
            promptLabel.setFont(regularFont);
            statusPanel.add(promptLabel, BorderLayout.NORTH);
            statusPanel.add(statusCombo, BorderLayout.CENTER);
            
            int result = JOptionPane.showConfirmDialog(
                historyDialog, 
                statusPanel, 
                "Update Rental Status", 
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (result == JOptionPane.OK_OPTION) {
                String newStatus = (String) statusCombo.getSelectedItem();
                
                // Update the rental status in the database
                try {
                    Connection conn = DBKonek.getConnection();
                    if (conn != null) {
                        PreparedStatement updateStmt = conn.prepareStatement(
                            "UPDATE rental_logs SET Status = ? WHERE log_id = ?"
                        );
                        updateStmt.setString(1, newStatus);
                        updateStmt.setInt(2, logId);
                        int rowsUpdated = updateStmt.executeUpdate();
                        updateStmt.close();
                        
                        if (rowsUpdated > 0) {
                            // Update machine status if rental status changed to Completed
                            if ("Completed".equals(newStatus)) {
                                // Get the machine ID first
                                PreparedStatement getMachineStmt = conn.prepareStatement(
                                    "SELECT machine_id FROM rental_logs WHERE log_id = ?"
                                );
                                getMachineStmt.setInt(1, logId);
                                ResultSet machineRs = getMachineStmt.executeQuery();
                                
                                if (machineRs.next()) {
                                    int machineId = machineRs.getInt("machine_id");
                                    
                                    // Update machine status to Available
                                    PreparedStatement updateMachineStmt = conn.prepareStatement(
                                        "UPDATE videoke_machines SET status = 'Available' WHERE machine_id = ?"
                                    );
                                    updateMachineStmt.setInt(1, machineId);
                                    updateMachineStmt.executeUpdate();
                                    updateMachineStmt.close();
                                }
                                machineRs.close();
                                getMachineStmt.close();
                            } else if ("Rented".equals(newStatus)) {
                                // Update machine status to Rented
                                PreparedStatement getMachineStmt = conn.prepareStatement(
                                    "SELECT machine_id FROM rental_logs WHERE log_id = ?"
                                );
                                getMachineStmt.setInt(1, logId);
                                ResultSet machineRs = getMachineStmt.executeQuery();
                                
                                if (machineRs.next()) {
                                    int machineId = machineRs.getInt("machine_id");
                                    
                                    PreparedStatement updateMachineStmt = conn.prepareStatement(
                                        "UPDATE videoke_machines SET status = 'Rented' WHERE machine_id = ?"
                                    );
                                    updateMachineStmt.setInt(1, machineId);
                                    updateMachineStmt.executeUpdate();
                                    updateMachineStmt.close();
                                }
                                machineRs.close();
                                getMachineStmt.close();
                            }
                            
                            // Update the table
                            historyTable.setValueAt(newStatus, selectedHistoryRow, 4);
                            historyTable.repaint();
                            
                            JOptionPane.showMessageDialog(
                                historyDialog,
                                "Rental status updated successfully!",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                        historyDialog,
                        "Error updating rental status: " + ex.getMessage(),
                        "Database Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });
        
        printButton.addActionListener(e -> {
            try {
                // Create a PDF with customer rental history
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Rental History PDF");
                fileChooser.setSelectedFile(new File(customerName.replaceAll("\\s", "_") + "_rental_history.pdf"));
                fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));
                
                int userSelection = fileChooser.showSaveDialog(historyDialog);
                
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = fileChooser.getSelectedFile();
                    if (!fileToSave.getName().toLowerCase().endsWith(".pdf")) {
                        fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
                    }
                    
                    JOptionPane.showMessageDialog(
                        historyDialog,
                        "Functionality to save PDF will be implemented here.\nFile: " + fileToSave.getPath(),
                        "Print to PDF",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                    historyDialog,
                    "Error generating PDF: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        closeButton.addActionListener(e -> historyDialog.dispose());
        
        // Assemble dialog
        historyDialog.add(customerInfoPanel, BorderLayout.NORTH);
        historyDialog.add(scrollPane, BorderLayout.CENTER);
        historyDialog.add(footerPanel, BorderLayout.SOUTH);
        
        // Show dialog
        historyDialog.setVisible(true);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void initReservationManagementPanel() {
    // Clear existing components
    jPanel14.removeAll();
    
    // Set modern layout with proper padding
    jPanel14.setLayout(new BorderLayout(0, 10));
    jPanel14.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
    jPanel14.setBackground(new Color(240, 248, 255));
    
    // Use consistent font: Segoe UI, PLAIN, 12
    java.awt.Font regularFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
    java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
    java.awt.Font headerFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14);
    
    // Create stylish header panel
    JPanel headerPanel = new JPanel(new BorderLayout(10, 0));
    headerPanel.setOpaque(false);
    
    JLabel titleLabel = new JLabel("Reservation Management");
    titleLabel.setFont(headerFont);
    titleLabel.setForeground(new Color(33, 33, 33));
    headerPanel.add(titleLabel, BorderLayout.WEST);
    
    // Create search panel with modern styling
    JPanel searchPanel = new JPanel(new BorderLayout(8, 0));
    searchPanel.setPreferredSize(new Dimension(300, 32));
    searchPanel.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
        BorderFactory.createEmptyBorder(2, 8, 2, 8)
    ));
    searchPanel.setBackground(Color.WHITE);
    
    JLabel searchIcon = new JLabel("🔍");
    searchIcon.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    searchIcon.setForeground(new Color(150, 150, 150));
    
    JTextField searchField = new JTextField();
    searchField.setFont(regularFont);
    searchField.setBorder(null);
    searchField.setOpaque(false);
    searchField.setToolTipText("Search by customer name, machine or date");
    
    searchPanel.add(searchIcon, BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);
    
    // Create status filter dropdown
    JPanel filterPanel = new JPanel(new BorderLayout(5, 0));
    filterPanel.setOpaque(false);
    
    JLabel filterLabel = new JLabel("Status: ");
    filterLabel.setFont(regularFont);
    
    String[] statuses = {"All", "Pending", "Confirmed", "Cancelled"};
    JComboBox<String> statusCombo = new JComboBox<>(statuses);
    statusCombo.setFont(regularFont);
    statusCombo.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
    statusCombo.setPreferredSize(new Dimension(120, 28));
    
    filterPanel.add(filterLabel, BorderLayout.WEST);
    filterPanel.add(statusCombo, BorderLayout.CENTER);
    
    // Create combined search area with both components
    JPanel searchAreaPanel = new JPanel(new BorderLayout(15, 0));
    searchAreaPanel.setOpaque(false);
    searchAreaPanel.add(filterPanel, BorderLayout.WEST);
    searchAreaPanel.add(searchPanel, BorderLayout.CENTER);
    
    headerPanel.add(searchAreaPanel, BorderLayout.EAST);
    jPanel14.add(headerPanel, BorderLayout.NORTH);
    
    // Set up table model with proper column names
    DefaultTableModel model = new DefaultTableModel(
        new Object[][] {},
        new String[] {"ID", "Customer Name", "Machine", "Start Date", "End Date", "Status", "Created Date"}
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 0) return Integer.class;
            return String.class;
        }
    };
    
    JTable reservationTable = new JTable(model);
    reservationTable.setRowHeight(28);
    reservationTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Enable horizontal scrolling
    reservationTable.setShowGrid(true);
    reservationTable.setGridColor(new Color(240, 240, 240));
    reservationTable.setFont(regularFont);
    reservationTable.getTableHeader().setFont(boldFont);
    reservationTable.getTableHeader().setOpaque(false);
    reservationTable.getTableHeader().setBackground(new Color(245, 245, 245));
    reservationTable.setSelectionBackground(new Color(225, 240, 255));
    reservationTable.setSelectionForeground(Color.BLACK);
    reservationTable.setRowMargin(3);
    
    // Set column widths
    reservationTable.getColumnModel().getColumn(0).setPreferredWidth(50);   // ID
    reservationTable.getColumnModel().getColumn(1).setPreferredWidth(150);  // Customer Name
    reservationTable.getColumnModel().getColumn(2).setPreferredWidth(150);  // Machine
    reservationTable.getColumnModel().getColumn(3).setPreferredWidth(100);  // Start Date
    reservationTable.getColumnModel().getColumn(4).setPreferredWidth(100);  // End Date
    reservationTable.getColumnModel().getColumn(5).setPreferredWidth(100);  // Status
    reservationTable.getColumnModel().getColumn(6).setPreferredWidth(120);  // Created Date
    
    // Create scrollpane for the table with better styling
    JScrollPane scrollPane = new JScrollPane(reservationTable);
    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
    scrollPane.setViewportBorder(null);
    scrollPane.getViewport().setBackground(Color.WHITE);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    jPanel14.add(scrollPane, BorderLayout.CENTER);
    
    // Apply color coding to status cells
    reservationTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = super.getTableCellRendererComponent(
                table, value, isSelected, hasFocus, row, column);
            
            c.setFont(regularFont); // Ensure consistent font in cells
            
            if (!isSelected) {
                if (column == 5) { // Status column
                    String status = value.toString();
                    if ("Confirmed".equals(status)) {
                        c.setBackground(new Color(232, 245, 233)); // Light green
                    } else if ("Pending".equals(status)) {
                        c.setBackground(new Color(255, 243, 224)); // Light orange
                    } else if ("Cancelled".equals(status)) {
                        c.setBackground(new Color(253, 237, 237)); // Light red
                    } else {
                        c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                    }
                } else {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(248, 248, 248));
                }
            }
            
            return c;
        }
    });
    
    // Create actions panel at the bottom with modern buttons
    JPanel actionsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    actionsPanel.setOpaque(false);
    
    JButton addButton = createStyledButton("New Reservation", new Color(76, 175, 80));
    addButton.setFont(boldFont);
    JButton editButton = createStyledButton("Edit", new Color(33, 150, 243));
    editButton.setFont(boldFont);
    JButton updateStatusButton = createStyledButton("Update Status", new Color(255, 152, 0));
    updateStatusButton.setFont(boldFont);
    JButton cancelButton = createStyledButton("Cancel Reservation", new Color(244, 67, 54));
    cancelButton.setFont(boldFont);
    JButton refreshButton = createStyledButton("Refresh", new Color(158, 158, 158));
    refreshButton.setFont(boldFont);
    
    // Add button actions
    addButton.addActionListener(e -> addNewReservation());
    editButton.addActionListener(e -> editSelectedReservation(reservationTable));
    updateStatusButton.addActionListener(e -> updateReservationStatus(reservationTable));
    cancelButton.addActionListener(e -> cancelSelectedReservation(reservationTable));
    refreshButton.addActionListener(e -> loadReservationData(model, "All"));
    
    actionsPanel.add(addButton);
    actionsPanel.add(editButton);
    actionsPanel.add(updateStatusButton);
    actionsPanel.add(cancelButton);
    actionsPanel.add(refreshButton);
    
    JPanel bottomPanel = new JPanel(new BorderLayout());
    bottomPanel.setOpaque(false);
    bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
    
    // Add info label on the left side showing record count
    JLabel countLabel = new JLabel("0 reservations found");
    countLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.ITALIC, 12));
    countLabel.setForeground(new Color(100, 100, 100));
    
    // Update the count label when data is loaded
    model.addTableModelListener(e -> {
        int count = model.getRowCount();
        countLabel.setText(count + (count == 1 ? " reservation found" : " reservations found"));
    });
    
    bottomPanel.add(countLabel, BorderLayout.WEST);
    bottomPanel.add(actionsPanel, BorderLayout.EAST);
    
    jPanel14.add(bottomPanel, BorderLayout.SOUTH);
    
    // Add search functionality
    searchField.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) { 
            String selectedStatus = (String) statusCombo.getSelectedItem();
            searchReservations(model, searchField.getText(), selectedStatus); 
        }
        @Override
        public void removeUpdate(DocumentEvent e) { 
            String selectedStatus = (String) statusCombo.getSelectedItem();
            searchReservations(model, searchField.getText(), selectedStatus); 
        }
        @Override
        public void changedUpdate(DocumentEvent e) { 
            String selectedStatus = (String) statusCombo.getSelectedItem();
            searchReservations(model, searchField.getText(), selectedStatus); 
        }
    });
    
    // Add status filter action
    statusCombo.addActionListener(e -> {
        String selectedStatus = (String) statusCombo.getSelectedItem();
        searchReservations(model, searchField.getText(), selectedStatus);
    });
    
    // Add double-click listener to table for quick editing
    reservationTable.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                editSelectedReservation(reservationTable);
            }
        }
    });
    
    // Load reservation data initially
    loadReservationData(model, "All");
}

/**
 * Loads reservation data from the database
 * @param model The table model to populate
 * @param statusFilter Filter by status (use "All" for no filter)
 */
private void loadReservationData(DefaultTableModel model, String statusFilter) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) {
        JOptionPane.showMessageDialog(null, "Database connection failed.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        model.setRowCount(0); // Clear existing data
        
        StringBuilder queryBuilder = new StringBuilder(
            "SELECT reservation_id, customer_name, machine_name, " +
            "reservation_start_date, reservation_end_date, status, created_at " +
            "FROM reservations "
        );
        
        if (!"All".equals(statusFilter)) {
            queryBuilder.append("WHERE status = ? ");
        }
        
        queryBuilder.append("ORDER BY created_at DESC");
        
        pstmt = connection.prepareStatement(queryBuilder.toString());
        
        if (!"All".equals(statusFilter)) {
            pstmt.setString(1, statusFilter);
        }
        
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("reservation_id"),
                rs.getString("customer_name"),
                rs.getString("machine_name"),
                formatDate(rs.getDate("reservation_start_date")),
                formatDate(rs.getDate("reservation_end_date")),
                rs.getString("status"),
                formatDate(rs.getTimestamp("created_at"))
            });
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error loading reservation data: " + e.getMessage(), 
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

/**
 * Format a date object to a string
 */
private String formatDate(java.util.Date date) {
    if (date == null) return "";
    SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
    return sdf.format(date);
}

/**
 * Search reservations based on input text and status filter
 */
private void searchReservations(DefaultTableModel model, String searchText, String statusFilter) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) return;
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try {
        model.setRowCount(0); // Clear existing data
        
        if (searchText == null || searchText.trim().isEmpty()) {
            // If search is empty, just load with status filter
            loadReservationData(model, statusFilter);
            return;
        }
        
        StringBuilder queryBuilder = new StringBuilder(
            "SELECT reservation_id, customer_name, machine_name, " +
            "reservation_start_date, reservation_end_date, status, created_at " +
            "FROM reservations WHERE " +
            "(customer_name LIKE ? OR machine_name LIKE ? OR additional_item LIKE ?) "
        );
        
        if (!"All".equals(statusFilter)) {
            queryBuilder.append("AND status = ? ");
        }
        
        queryBuilder.append("ORDER BY created_at DESC");
        
        pstmt = connection.prepareStatement(queryBuilder.toString());
        
        String wildcardSearch = "%" + searchText + "%";
        pstmt.setString(1, wildcardSearch);
        pstmt.setString(2, wildcardSearch);
        pstmt.setString(3, wildcardSearch);
        
        if (!"All".equals(statusFilter)) {
            pstmt.setString(4, statusFilter);
        }
        
        rs = pstmt.executeQuery();
        
        while (rs.next()) {
            model.addRow(new Object[] {
                rs.getInt("reservation_id"),
                rs.getString("customer_name"),
                rs.getString("machine_name"),
                formatDate(rs.getDate("reservation_start_date")),
                formatDate(rs.getDate("reservation_end_date")),
                rs.getString("status"),
                formatDate(rs.getTimestamp("created_at"))
            });
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

/**
 * Add a new reservation
 */
private void addNewReservation() {
    // Create a reservation dialog
    JDialog dialog = new JDialog(this, "Add New Reservation", true);
    dialog.setLayout(new BorderLayout());
    dialog.setSize(450, 450);
    dialog.setLocationRelativeTo(this);
    dialog.setResizable(false);
    
    // Main panel with padding
    JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
    mainPanel.setBackground(Color.WHITE);
    
    // Form panel
    JPanel formPanel = new JPanel(new GridBagLayout());
    formPanel.setBackground(Color.WHITE);
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.anchor = GridBagConstraints.WEST;
    
    // Use consistent font: Segoe UI, PLAIN, 12
    java.awt.Font regularFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
    java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
    
    // Create styled components
    JLabel nameLabel = new JLabel("Customer Name:");
    nameLabel.setFont(boldFont);
    JLabel contactLabel = new JLabel("Contact Number:");
    contactLabel.setFont(boldFont);
    JLabel emailLabel = new JLabel("Email Address:");
    emailLabel.setFont(boldFont);
    JLabel addressLabel = new JLabel("Address:");
    addressLabel.setFont(boldFont);
    JLabel machineLabel = new JLabel("Machine:");
    machineLabel.setFont(boldFont);
    JLabel startDateLabel = new JLabel("Start Date:");
    startDateLabel.setFont(boldFont);
    JLabel endDateLabel = new JLabel("End Date:");
    endDateLabel.setFont(boldFont);
    JLabel additionalItemsLabel = new JLabel("Additional Items:");
    additionalItemsLabel.setFont(boldFont);
    
    JTextField nameField = new JTextField(20);
    nameField.setFont(regularFont);
    JTextField contactField = new JTextField(20);
    contactField.setFont(regularFont);
    JTextField emailField = new JTextField(20);
    emailField.setFont(regularFont);
    JTextField addressField = new JTextField(20);
    addressField.setFont(regularFont);
    
    // Get machine list from database
    JComboBox<String> machineCombo = new JComboBox<>();
    machineCombo.setFont(regularFont);
    loadAvailableMachines(machineCombo);
    
    JDateChooser startDateChooser = new JDateChooser();
    startDateChooser.setDateFormatString("yyyy-MM-dd");
    startDateChooser.setFont(regularFont);
    startDateChooser.setDate(new java.util.Date()); // Set to today by default
    
    JDateChooser endDateChooser = new JDateChooser();
    endDateChooser.setDateFormatString("yyyy-MM-dd");
    endDateChooser.setFont(regularFont);
    
    // Set end date to tomorrow by default
    Calendar tomorrow = Calendar.getInstance();
    tomorrow.add(Calendar.DATE, 1);
    endDateChooser.setDate(tomorrow.getTime());
    
    JTextArea additionalItemsArea = new JTextArea(3, 20);
    additionalItemsArea.setFont(regularFont);
    additionalItemsArea.setLineWrap(true);
    additionalItemsArea.setWrapStyleWord(true);
    JScrollPane additionalItemsScroll = new JScrollPane(additionalItemsArea);
    
    // Add components to form with GridBagLayout
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 0.3;
    formPanel.add(nameLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 0;
    gbc.weightx = 0.7;
    formPanel.add(nameField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.weightx = 0.3;
    formPanel.add(contactLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 1;
    gbc.weightx = 0.7;
    formPanel.add(contactField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.weightx = 0.3;
    formPanel.add(emailLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 2;
    gbc.weightx = 0.7;
    formPanel.add(emailField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.weightx = 0.3;
    formPanel.add(addressLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 3;
    gbc.weightx = 0.7;
    formPanel.add(addressField, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 4;
    gbc.weightx = 0.3;
    formPanel.add(machineLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 4;
    gbc.weightx = 0.7;
    formPanel.add(machineCombo, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 5;
    gbc.weightx = 0.3;
    formPanel.add(startDateLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 5;
    gbc.weightx = 0.7;
    formPanel.add(startDateChooser, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 6;
    gbc.weightx = 0.3;
    formPanel.add(endDateLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 6;
    gbc.weightx = 0.7;
    formPanel.add(endDateChooser, gbc);
    
    gbc.gridx = 0;
    gbc.gridy = 7;
    gbc.weightx = 0.3;
    formPanel.add(additionalItemsLabel, gbc);
    
    gbc.gridx = 1;
    gbc.gridy = 7;
    gbc.weightx = 0.7;
    gbc.gridheight = 2;
    formPanel.add(additionalItemsScroll, gbc);
    gbc.gridheight = 1;
    
    // Button panel
    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
    buttonPanel.setBackground(Color.WHITE);
    
    JButton saveButton = new JButton("Add Reservation");
    saveButton.setFont(boldFont);
    saveButton.setBackground(new Color(76, 175, 80));
    saveButton.setForeground(Color.WHITE);
    saveButton.setFocusPainted(false);
    saveButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    
    JButton cancelButton = new JButton("Cancel");
    cancelButton.setFont(regularFont);
    cancelButton.setBackground(new Color(240, 240, 240));
    cancelButton.setFocusPainted(false);
    cancelButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
    
    buttonPanel.add(saveButton);
    buttonPanel.add(cancelButton);
    
    // Add action listeners
    saveButton.addActionListener(e -> {
        // Validate inputs
        if (nameField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Customer name is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (contactField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(dialog, "Contact number is required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (machineCombo.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(dialog, "Please select a machine", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (startDateChooser.getDate() == null || endDateChooser.getDate() == null) {
            JOptionPane.showMessageDialog(dialog, "Both start and end dates are required", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Get machine_id from selected machine
        String selectedMachineName = (String) machineCombo.getSelectedItem();
        int machineId = getMachineId(selectedMachineName);
        
        if (machineId == -1) {
            JOptionPane.showMessageDialog(dialog, "Invalid machine selection", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Save to database
        try {
            saveReservation(
                null,
                nameField.getText().trim(),
                contactField.getText().trim(),
                emailField.getText().trim(),
                addressField.getText().trim(),
                machineId,
                selectedMachineName,
                startDateChooser.getDate(),
                endDateChooser.getDate(),
                additionalItemsArea.getText().trim()
            );
            dialog.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(dialog, "Error saving reservation: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    });
    
    cancelButton.addActionListener(e -> dialog.dispose());
    
    // Add panels to dialog
    mainPanel.add(formPanel, BorderLayout.CENTER);
    mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    dialog.add(mainPanel);
    
    // Show dialog
    dialog.setVisible(true);
}

/**
 * Load available machines into a combo box
 */
private void loadAvailableMachines(JComboBox<String> machineCombo) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) return;
    
    try {
        String query = "SELECT machine_id, machine_name FROM videoke_machines WHERE status = 'Available' ORDER BY machine_name";
        PreparedStatement pstmt = connection.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        
        machineCombo.removeAllItems();
        
        while (rs.next()) {
            machineCombo.addItem(rs.getString("machine_name"));
        }
        
        rs.close();
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

/**
 * Get machine ID from machine name
 */
private int getMachineId(String machineName) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) return -1;
    
    try {
        String query = "SELECT machine_id FROM videoke_machines WHERE machine_name = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, machineName);
        ResultSet rs = pstmt.executeQuery();
        
        int machineId = -1;
        if (rs.next()) {
            machineId = rs.getInt("machine_id");
        }
        
        rs.close();
        pstmt.close();
        
        return machineId;
    } catch (SQLException e) {
        e.printStackTrace();
        return -1;
    }
}

/**
 * Save a reservation to the database
 */
private void saveReservation(
        Integer reservationId,
        String customerName,
        String contactNumber,
        String email,
        String address,
        int machineId,
        String machineName,
        java.util.Date startDate,
        java.util.Date endDate,
        String additionalItems) throws SQLException {
    
    Connection connection = DBKonek.getConnection();
    if (connection == null) throw new SQLException("Database connection failed");
    
    // Convert to SQL dates
    java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
    java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
    
    PreparedStatement pstmt = null;
    
    try {
        if (reservationId == null) {
            // Insert new reservation
            pstmt = connection.prepareStatement(
                "INSERT INTO reservations (customer_name, contact_number, email, address, " +
                "machine_id, machine_name, reservation_start_date, reservation_end_date, " +
                "additional_item, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pending')"
            );
        } else {
            // Update existing reservation
            pstmt = connection.prepareStatement(
                "UPDATE reservations SET customer_name = ?, contact_number = ?, email = ?, " +
                "address = ?, machine_id = ?, machine_name = ?, reservation_start_date = ?, " +
                "reservation_end_date = ?, additional_item = ? WHERE reservation_id = ?"
            );
            pstmt.setInt(10, reservationId);
        }
        
        pstmt.setString(1, customerName);
        pstmt.setString(2, contactNumber);
        pstmt.setString(3, email);
        pstmt.setString(4, address);
        pstmt.setInt(5, machineId);
        pstmt.setString(6, machineName);
        pstmt.setDate(7, sqlStartDate);
        pstmt.setDate(8, sqlEndDate);
        pstmt.setString(9, additionalItems);
        
        int rowsAffected = pstmt.executeUpdate();
        
        if (rowsAffected > 0) {
            // Update machine status to Reserved
            PreparedStatement updateMachineStmt = connection.prepareStatement(
                "UPDATE videoke_machines SET status = 'Reserved' WHERE machine_id = ?"
            );
            updateMachineStmt.setInt(1, machineId);
            updateMachineStmt.executeUpdate();
            updateMachineStmt.close();
            
            JOptionPane.showMessageDialog(
                this, 
                reservationId == null ? "Reservation created successfully!" : "Reservation updated successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
            );
        }
    } finally {
        if (pstmt != null) pstmt.close();
    }
}

/**
 * Edit an existing reservation
 */
private void editSelectedReservation(JTable reservationTable) {
    int selectedRow = reservationTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a reservation to edit", 
                                     "No Selection", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
    String status = reservationTable.getValueAt(selectedRow, 5).toString();
    
    // Don't allow editing cancelled reservations
    if ("Cancelled".equals(status)) {
        JOptionPane.showMessageDialog(this, "Cannot edit a cancelled reservation", 
                                     "Invalid Action", JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // Load the reservation details
    Connection connection = DBKonek.getConnection();
    if (connection == null) return;
    
    try {
        String query = "SELECT * FROM reservations WHERE reservation_id = ?";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, reservationId);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            // Create edit dialog similar to add dialog
            JDialog dialog = new JDialog(this, "Edit Reservation #" + reservationId, true);
            dialog.setLayout(new BorderLayout());
            dialog.setSize(450, 450);
            dialog.setLocationRelativeTo(this);
            
            // Main panel
            JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
            mainPanel.setBackground(Color.WHITE);
            
            // Form panel
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(Color.WHITE);
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.anchor = GridBagConstraints.WEST;
            
            // Use consistent font: Segoe UI, PLAIN, 12
            java.awt.Font regularFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
            java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
            
            // Fields (similar to addNewReservation method)
            JLabel nameLabel = new JLabel("Customer Name:");
            nameLabel.setFont(boldFont);
            JLabel contactLabel = new JLabel("Contact Number:");
            contactLabel.setFont(boldFont);
            JLabel emailLabel = new JLabel("Email Address:");
            emailLabel.setFont(boldFont);
            JLabel addressLabel = new JLabel("Address:");
            addressLabel.setFont(boldFont);
            JLabel machineLabel = new JLabel("Machine:");
            machineLabel.setFont(boldFont);
            JLabel startDateLabel = new JLabel("Start Date:");
            startDateLabel.setFont(boldFont);
            JLabel endDateLabel = new JLabel("End Date:");
            endDateLabel.setFont(boldFont);
            JLabel additionalItemsLabel = new JLabel("Additional Items:");
            additionalItemsLabel.setFont(boldFont);
            JLabel statusLabel = new JLabel("Status:");
            statusLabel.setFont(boldFont);
            
            JTextField nameField = new JTextField(rs.getString("customer_name"), 20);
            nameField.setFont(regularFont);
            JTextField contactField = new JTextField(rs.getString("contact_number"), 20);
            contactField.setFont(regularFont);
            JTextField emailField = new JTextField(rs.getString("email"), 20);
            emailField.setFont(regularFont);
            JTextField addressField = new JTextField(rs.getString("address"), 20);
            addressField.setFont(regularFont);
            
            // Machine combo with current machine and available machines
            JComboBox<String> machineCombo = new JComboBox<>();
            machineCombo.setFont(regularFont);
            loadAvailableMachinesForEdit(machineCombo, rs.getString("machine_name"));
            
            // Dates
            JDateChooser startDateChooser = new JDateChooser();
            startDateChooser.setDateFormatString("yyyy-MM-dd");
            startDateChooser.setFont(regularFont);
            startDateChooser.setDate(rs.getDate("reservation_start_date"));
            
            JDateChooser endDateChooser = new JDateChooser();
            endDateChooser.setDateFormatString("yyyy-MM-dd");
            endDateChooser.setFont(regularFont);
            endDateChooser.setDate(rs.getDate("reservation_end_date"));
            
            JTextArea additionalItemsArea = new JTextArea(3, 20);
            additionalItemsArea.setText(rs.getString("additional_item"));
            additionalItemsArea.setFont(regularFont);
            additionalItemsArea.setLineWrap(true);
            additionalItemsArea.setWrapStyleWord(true);
            JScrollPane additionalItemsScroll = new JScrollPane(additionalItemsArea);
            
            // Status dropdown
            String[] statusOptions = {"Pending", "Confirmed", "Cancelled"};
            JComboBox<String> statusCombo = new JComboBox<>(statusOptions);
            statusCombo.setFont(regularFont);
            statusCombo.setSelectedItem(rs.getString("status"));
            
            // Add components to form (similar layout to add reservation)
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 0.3;
            formPanel.add(nameLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 0.7;
            formPanel.add(nameField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 1;
            gbc.weightx = 0.3;
            formPanel.add(contactLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 1;
            gbc.weightx = 0.7;
            formPanel.add(contactField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 2;
            gbc.weightx = 0.3;
            formPanel.add(emailLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 2;
            gbc.weightx = 0.7;
            formPanel.add(emailField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 3;
            gbc.weightx = 0.3;
            formPanel.add(addressLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 3;
            gbc.weightx = 0.7;
            formPanel.add(addressField, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 4;
            gbc.weightx = 0.3;
            formPanel.add(machineLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 4;
            gbc.weightx = 0.7;
            formPanel.add(machineCombo, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 5;
            gbc.weightx = 0.3;
            formPanel.add(startDateLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 5;
            gbc.weightx = 0.7;
            formPanel.add(startDateChooser, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 6;
            gbc.weightx = 0.3;
            formPanel.add(endDateLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 6;
            gbc.weightx = 0.7;
            formPanel.add(endDateChooser, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 7;
            gbc.weightx = 0.3;
            formPanel.add(additionalItemsLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 7;
            gbc.weightx = 0.7;
            gbc.gridheight = 2;
            formPanel.add(additionalItemsScroll, gbc);
            gbc.gridheight = 1;
            
            gbc.gridx = 0;
            gbc.gridy = 9;
            gbc.weightx = 0.3;
            formPanel.add(statusLabel, gbc);
            
            gbc.gridx = 1;
            gbc.gridy = 9;
            gbc.weightx = 0.7;
            formPanel.add(statusCombo, gbc);
            
            // Save button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
            buttonPanel.setBackground(Color.WHITE);
            
            JButton saveButton = new JButton("Save Changes");
            saveButton.setFont(boldFont);
            saveButton.setBackground(new Color(76, 175, 80));
            saveButton.setForeground(Color.WHITE);
            saveButton.setFocusPainted(false);
            saveButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            
            JButton cancelButton = new JButton("Cancel");
            cancelButton.setFont(regularFont);
            cancelButton.setBackground(new Color(240, 240, 240));
            cancelButton.setFocusPainted(false);
            cancelButton.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
            
            buttonPanel.add(saveButton);
            buttonPanel.add(cancelButton);
            
            // Track original values for validation
            final int originalMachineId = rs.getInt("machine_id");
            final String originalStatus = rs.getString("status");
            
            // Button actions
            saveButton.addActionListener(e -> {
                // Validate inputs (similar to add method)
                if (nameField.getText().trim().isEmpty() || contactField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Name and contact are required fields", 
                                                "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                String selectedStatus = (String) statusCombo.getSelectedItem();
                String selectedMachine = (String) machineCombo.getSelectedItem();
                int machineId = getMachineId(selectedMachine);
                
                try {
                    // If status changed to Cancelled, update machine status
                    if ("Cancelled".equals(selectedStatus) && !originalStatus.equals("Cancelled")) {
                        // Release the machine if cancelling
                        PreparedStatement updateMachineStmt = connection.prepareStatement(
                            "UPDATE videoke_machines SET status = 'Available' WHERE machine_id = ?"
                        );
                        updateMachineStmt.setInt(1, originalMachineId);
                        updateMachineStmt.executeUpdate();
                        updateMachineStmt.close();
                    }
                    // If machine changed, update both old and new machine statuses
                    else if (machineId != originalMachineId) {
                        // Free up old machine
                        PreparedStatement updateOldMachineStmt = connection.prepareStatement(
                            "UPDATE videoke_machines SET status = 'Available' WHERE machine_id = ?"
                        );
                        updateOldMachineStmt.setInt(1, originalMachineId);
                        updateOldMachineStmt.executeUpdate();
                        updateOldMachineStmt.close();
                        
                        // Reserve new machine
                        PreparedStatement updateNewMachineStmt = connection.prepareStatement(
                            "UPDATE videoke_machines SET status = 'Reserved' WHERE machine_id = ?"
                        );
                        updateNewMachineStmt.setInt(1, machineId);
                        updateNewMachineStmt.executeUpdate();
                        updateNewMachineStmt.close();
                    }
                    
                    // Update reservation
                    PreparedStatement updateStmt = connection.prepareStatement(
                        "UPDATE reservations SET customer_name = ?, contact_number = ?, " +
                        "email = ?, address = ?, machine_id = ?, machine_name = ?, " +
                        "reservation_start_date = ?, reservation_end_date = ?, " +
                        "additional_item = ?, status = ? WHERE reservation_id = ?"
                    );
                    
                    updateStmt.setString(1, nameField.getText().trim());
                    updateStmt.setString(2, contactField.getText().trim());
                    updateStmt.setString(3, emailField.getText().trim());
                    updateStmt.setString(4, addressField.getText().trim());
                    updateStmt.setInt(5, machineId);
                    updateStmt.setString(6, selectedMachine);
                    updateStmt.setDate(7, new java.sql.Date(startDateChooser.getDate().getTime()));
                    updateStmt.setDate(8, new java.sql.Date(endDateChooser.getDate().getTime()));
                    updateStmt.setString(9, additionalItemsArea.getText());
                    updateStmt.setString(10, selectedStatus);
                    updateStmt.setInt(11, reservationId);
                    
                    int rowsUpdated = updateStmt.executeUpdate();
                    
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(dialog, 
                                                     "Reservation updated successfully", 
                                                     "Success", 
                                                     JOptionPane.INFORMATION_MESSAGE);
                        
                        // Refresh table data
                        DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
                        loadReservationData(model, "All");
                        
                        dialog.dispose();
                    }
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(dialog, 
                                                 "Error updating reservation: " + ex.getMessage(), 
                                                 "Database Error", 
                                                 JOptionPane.ERROR_MESSAGE);
                }
            });
            
            cancelButton.addActionListener(e -> dialog.dispose());
            
            // Assemble dialog
            mainPanel.add(formPanel, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            dialog.add(mainPanel);
            
            // Show dialog
            dialog.setVisible(true);
        }
        
        rs.close();
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading reservation data: " + e.getMessage(), 
                                     "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

/**
 * Load both the current machine and available machines for editing
 */
private void loadAvailableMachinesForEdit(JComboBox<String> machineCombo, String currentMachineName) {
    Connection connection = DBKonek.getConnection();
    if (connection == null) return;
    
    try {
        // First add the current machine
        machineCombo.addItem(currentMachineName);
        
        // Then add available machines
        String query = "SELECT machine_name FROM videoke_machines WHERE status = 'Available' AND machine_name != ? ORDER BY machine_name";
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setString(1, currentMachineName);
        ResultSet rs = pstmt.executeQuery();
        
        while (rs.next()) {
            machineCombo.addItem(rs.getString("machine_name"));
        }
        
        rs.close();
        pstmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

/**
 * Update the status of a reservation
 */
private void updateReservationStatus(JTable reservationTable) {
    int selectedRow = reservationTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a reservation to update", 
                                     "No Selection", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
    String currentStatus = reservationTable.getValueAt(selectedRow, 5).toString();
    String customerName = reservationTable.getValueAt(selectedRow, 1).toString();
    String machineName = reservationTable.getValueAt(selectedRow, 2).toString();
    
    // Create status selection dialog with consistent fonts
    java.awt.Font regularFont = new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 12);
    java.awt.Font boldFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
    
    String[] statuses = {"Pending", "Confirmed", "Cancelled"};
    JComboBox<String> statusCombo = new JComboBox<>(statuses);
    statusCombo.setFont(regularFont);
    statusCombo.setSelectedItem(currentStatus);
    
    JPanel statusPanel = new JPanel(new BorderLayout(10, 10));
    statusPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
    JLabel promptLabel = new JLabel("Update status for reservation:");
    promptLabel.setFont(boldFont);
    
    JLabel infoLabel = new JLabel("<html>" +
                                 "Customer: <b>" + customerName + "</b><br>" +
                                 "Machine: <b>" + machineName + "</b></html>");
    infoLabel.setFont(regularFont);
    
    JPanel infoPanel = new JPanel(new BorderLayout(5, 10));
    infoPanel.setOpaque(false);
    infoPanel.add(promptLabel, BorderLayout.NORTH);
    infoPanel.add(infoLabel, BorderLayout.CENTER);
    
    statusPanel.add(infoPanel, BorderLayout.NORTH);
    statusPanel.add(statusCombo, BorderLayout.CENTER);
    
    int result = JOptionPane.showConfirmDialog(
        this, 
        statusPanel, 
        "Update Reservation Status", 
        JOptionPane.OK_CANCEL_OPTION,
        JOptionPane.PLAIN_MESSAGE
    );
    
    if (result == JOptionPane.OK_OPTION) {
        String newStatus = (String) statusCombo.getSelectedItem();
        
        if (newStatus.equals(currentStatus)) {
            JOptionPane.showMessageDialog(this, "Status unchanged", "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            // First get the machine ID
            int machineId = -1;
            PreparedStatement getMachineStmt = connection.prepareStatement(
                "SELECT machine_id FROM reservations WHERE reservation_id = ?"
            );
            getMachineStmt.setInt(1, reservationId);
            ResultSet rs = getMachineStmt.executeQuery();
            
            if (rs.next()) {
                machineId = rs.getInt("machine_id");
            }
            
            rs.close();
            getMachineStmt.close();
            
            // Update status
            PreparedStatement updateStmt = connection.prepareStatement(
                "UPDATE reservations SET status = ? WHERE reservation_id = ?"
            );
            updateStmt.setString(1, newStatus);
            updateStmt.setInt(2, reservationId);
            
            int rowsAffected = updateStmt.executeUpdate();
            
            if (rowsAffected > 0) {
                // Update machine status based on reservation status
                if ("Cancelled".equals(newStatus)) {
                    // Release machine if reservation is cancelled
                    PreparedStatement updateMachineStmt = connection.prepareStatement(
                        "UPDATE videoke_machines SET status = 'Available' WHERE machine_id = ?"
                    );
                    updateMachineStmt.setInt(1, machineId);
                    updateMachineStmt.executeUpdate();
                    updateMachineStmt.close();
                }
                else if ("Confirmed".equals(newStatus)) {
                    // Confirm reservation
                    PreparedStatement updateMachineStmt = connection.prepareStatement(
                        "UPDATE videoke_machines SET status = 'Reserved' WHERE machine_id = ?"
                    );
                    updateMachineStmt.setInt(1, machineId);
                    updateMachineStmt.executeUpdate();
                    updateMachineStmt.close();
                }
                
                // Update table display
                JOptionPane.showMessageDialog(this, 
                                             "Reservation status updated successfully!", 
                                             "Success", 
                                             JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh table data
                DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
                loadReservationData(model, "All");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                                         "Error updating reservation status: " + e.getMessage(), 
                                         "Database Error", 
                                         JOptionPane.ERROR_MESSAGE);
        }
    }
}

/**
 * Cancel a selected reservation
 */
private void cancelSelectedReservation(JTable reservationTable) {
    int selectedRow = reservationTable.getSelectedRow();
    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(this, "Please select a reservation to cancel", 
                                     "No Selection", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    int reservationId = (int) reservationTable.getValueAt(selectedRow, 0);
    String currentStatus = reservationTable.getValueAt(selectedRow, 5).toString();
    
    // Don't allow canceling already cancelled reservations
    if ("Cancelled".equals(currentStatus)) {
        JOptionPane.showMessageDialog(this, "This reservation is already cancelled", 
                                     "Information", JOptionPane.INFORMATION_MESSAGE);
        return;
    }
    
    int confirm = JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to cancel this reservation?\n\n" +
        "This will mark the reservation as 'Cancelled' and make the machine available for booking.",
        "Confirm Cancellation",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE
    );
    
    if (confirm == JOptionPane.YES_OPTION) {
        Connection connection = DBKonek.getConnection();
        if (connection == null) return;
        
        try {
            // First get the machine ID
            int machineId = -1;
            PreparedStatement getMachineStmt = connection.prepareStatement(
                "SELECT machine_id FROM reservations WHERE reservation_id = ?"
            );
            getMachineStmt.setInt(1, reservationId);
            ResultSet rs = getMachineStmt.executeQuery();
            
            if (rs.next()) {
                machineId = rs.getInt("machine_id");
            }
            
            rs.close();
            getMachineStmt.close();
            
            // Update reservation status
            PreparedStatement updateStmt = connection.prepareStatement(
                "UPDATE reservations SET status = 'Cancelled' WHERE reservation_id = ?"
            );
            updateStmt.setInt(1, reservationId);
            
            int rowsAffected = updateStmt.executeUpdate();
            
            if (rowsAffected > 0 && machineId > 0) {
                // Release the machine
                PreparedStatement updateMachineStmt = connection.prepareStatement(
                    "UPDATE videoke_machines SET status = 'Available' WHERE machine_id = ?"
                );
                updateMachineStmt.setInt(1, machineId);
                updateMachineStmt.executeUpdate();
                updateMachineStmt.close();
                
                JOptionPane.showMessageDialog(this, 
                                             "Reservation cancelled successfully!", 
                                             "Success", 
                                             JOptionPane.INFORMATION_MESSAGE);
                
                // Refresh table data
                DefaultTableModel model = (DefaultTableModel) reservationTable.getModel();
                loadReservationData(model, "All");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, 
                                         "Error cancelling reservation: " + e.getMessage(), 
                                         "Database Error", 
                                         JOptionPane.ERROR_MESSAGE);
        }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        dashboard = new javax.swing.JButton();
        customermngmnt = new javax.swing.JButton();
        managemachine = new javax.swing.JButton();
        systemhistory = new javax.swing.JButton();
        mngreservation = new javax.swing.JButton();
        logoutstaff = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        dashboardpanel = new javax.swing.JPanel();
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
        managemahcines = new javax.swing.JPanel();
        systemhistroy = new javax.swing.JPanel();
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

        managemachine.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        managemachine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usagemonitor.png"))); // NOI18N
        managemachine.setText("Manage Machines");
        managemachine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                managemachineActionPerformed(evt);
            }
        });
        jPanel1.add(managemachine, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 210, 40));

        systemhistory.setFont(new java.awt.Font("Monospaced", 1, 13)); // NOI18N
        systemhistory.setIcon(new javax.swing.ImageIcon(getClass().getResource("/equipmngmnt.png"))); // NOI18N
        systemhistory.setText("System History");
        systemhistory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                systemhistoryActionPerformed(evt);
            }
        });
        jPanel1.add(systemhistory, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 210, 40));

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

        dashboardpanel.setBackground(new java.awt.Color(204, 255, 255));
        dashboardpanel.setPreferredSize(new java.awt.Dimension(740, 490));
        dashboardpanel.setRequestFocusEnabled(false);
        dashboardpanel.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout dashboardpanelLayout = new javax.swing.GroupLayout(dashboardpanel);
        dashboardpanel.setLayout(dashboardpanelLayout);
        dashboardpanelLayout.setHorizontalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        dashboardpanelLayout.setVerticalGroup(
            dashboardpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Dashboard", dashboardpanel);

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
                    .addGap(0, 2, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 3, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Manage Rent", jPanel10);

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

        managemahcines.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout managemahcinesLayout = new javax.swing.GroupLayout(managemahcines);
        managemahcines.setLayout(managemahcinesLayout);
        managemahcinesLayout.setHorizontalGroup(
            managemahcinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        managemahcinesLayout.setVerticalGroup(
            managemahcinesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Manage Machines", managemahcines);

        systemhistroy.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout systemhistroyLayout = new javax.swing.GroupLayout(systemhistroy);
        systemhistroy.setLayout(systemhistroyLayout);
        systemhistroyLayout.setHorizontalGroup(
            systemhistroyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 740, Short.MAX_VALUE)
        );
        systemhistroyLayout.setVerticalGroup(
            systemhistroyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 495, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("System History", systemhistroy);

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
        booking.setText("Manage Rent ");
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
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_dashboardActionPerformed

    

    private void customermngmntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customermngmntActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_customermngmntActionPerformed

    private void systemhistoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_systemhistoryActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(4);
    }//GEN-LAST:event_systemhistoryActionPerformed

    private void managemachineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_managemachineActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(3);
    }//GEN-LAST:event_managemachineActionPerformed

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
        jTabbedPane1.setSelectedIndex(1);
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
                          + "   -   Wired Microphones\n"
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
            
            // Ask for rental dates - removed pickup date chooser
            JDateChooser rentalStartChooser = new JDateChooser();
            JDateChooser rentalEndChooser = new JDateChooser();
            
            rentalStartChooser.setDateFormatString("yyyy-MM-dd");
            rentalEndChooser.setDateFormatString("yyyy-MM-dd");
            
            // Default rental start date to today
            rentalStartChooser.setDate(new java.util.Date());
    
            // Simplified rental panel with just start and end date
            JPanel rentalPanel = new JPanel(new GridLayout(2, 2, 10, 10));
            rentalPanel.add(new JLabel("Rental Start Date:"));
            rentalPanel.add(rentalStartChooser);
            rentalPanel.add(new JLabel("Return Date:"));
            rentalPanel.add(rentalEndChooser);
    
            int rentalConfirm = JOptionPane.showConfirmDialog(null, rentalPanel, "Select Dates", JOptionPane.OK_CANCEL_OPTION);
            if (rentalConfirm != JOptionPane.OK_OPTION) return;
            
            java.util.Date rentalStartDate = rentalStartChooser.getDate();
            java.util.Date rentalEndDate = rentalEndChooser.getDate();
    
            if (rentalStartDate == null || rentalEndDate == null) {
                JOptionPane.showMessageDialog(null, "Both dates are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Validate dates
            if (rentalEndDate.before(rentalStartDate)) {
                JOptionPane.showMessageDialog(null, "Return date cannot be before start date!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Pickup date is same as start date
            java.util.Date pickupDate = rentalStartDate;
    
            // Calculate total days and cost
            long diffInMillies = rentalEndDate.getTime() - rentalStartDate.getTime();
            int days = (int) (diffInMillies / (1000 * 60 * 60 * 24)) + 1;
            double totalCost = (machinePrice * days) + additionalCost;
            
            // Show rental summary
            String rentalSummary = "Machine: " + machineName + "\n" +
                                "Pickup/Start Date: " + new SimpleDateFormat("yyyy-MM-dd").format(rentalStartDate) + "\n" +
                                "Return Date: " + new SimpleDateFormat("yyyy-MM-dd").format(rentalEndDate) + " (" + days + " days)\n" +
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
            // Updated payment type options to match the database enum values
            JComboBox<String> paymentTypeCombo = new JComboBox<>(new String[]{
                "Cash", "Credit Card", "Bank Transfer", "Mobile Payment"
            });
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
            java.sql.Date sqlPickupDate = new java.sql.Date(pickupDate.getTime());
            java.sql.Date sqlStartDate = new java.sql.Date(rentalStartDate.getTime());
            java.sql.Date sqlEndDate = new java.sql.Date(rentalEndDate.getTime());
            
            // Insert into rental_logs and get the generated ID
            PreparedStatement rentalStmt = kon.prepareStatement(
                    "INSERT INTO rental_logs (customer_name, contact_number, email, address, " +
                    "machine_id, machine_name, additional_item, pickup_date, rental_start_date, rental_end_date, Status) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, 'Pickup')", Statement.RETURN_GENERATED_KEYS);
    
            rentalStmt.setString(1, customerName);
            rentalStmt.setString(2, contactNumber);
            rentalStmt.setString(3, emailAddress);
            rentalStmt.setString(4, address);
            rentalStmt.setInt(5, machineId);
            rentalStmt.setString(6, machineName);
            rentalStmt.setString(7, additionalDetails);
            rentalStmt.setDate(8, sqlPickupDate);  // Pickup date is same as start date
            rentalStmt.setDate(9, sqlStartDate);
            rentalStmt.setDate(10, sqlEndDate);
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
                    "INSERT INTO payments (log_id, amount_paid, total_amount, payment_type) VALUES (?, ?, ?, ?)");
            paymentStmt.setInt(1, logId);  // Use the log_id from rental_logs
            paymentStmt.setDouble(2, amountPaid);
            paymentStmt.setDouble(3, totalCost);  // Add total_amount value
            paymentStmt.setString(4, paymentType);
            paymentStmt.executeUpdate();
            
            // Generate receipt
            double change = amountPaid - totalCost;
            StringBuilder receiptBuilder = new StringBuilder();
            receiptBuilder.append("\n====== VIDEOKE RENTAL RECEIPT ======\n")
                         .append("Date: ").append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date())).append("\n")
                         .append("Customer: ").append(customerName).append("\n")
                         .append("Contact: ").append(contactNumber).append("\n\n")
                         .append("Machine: ").append(machineName).append("\n")
                         .append("Pickup/Start Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(pickupDate)).append("\n")
                         .append("Return Date: ").append(new SimpleDateFormat("yyyy-MM-dd").format(rentalEndDate)).append("\n")
                         .append("Duration: ").append(days).append(" days\n\n")
                         .append("Status: Pickup\n\n");
                           
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
                          .append("  Machine pickup on:    \n")
                          .append("  ").append(new SimpleDateFormat("yyyy-MM-dd").format(pickupDate)).append("\n")
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
                        generatePdfReceipt(receipt, file, customerName, machineName, sqlPickupDate, sqlStartDate, sqlEndDate, paymentType);
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
                                String machineName, java.sql.Date pickupDate,
                                java.sql.Date startDate, java.sql.Date endDate, 
                                String paymentType) throws Exception {
    
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
    addTableRow(detailsTable, "Pickup/Start Date:", dateFormatter.format(pickupDate), boldFont, normalFont);
    addTableRow(detailsTable, "Return Date:", dateFormatter.format(endDate), boldFont, normalFont);
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
    private javax.swing.JPanel dashboardpanel;
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
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
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
    private javax.swing.JButton managemachine;
    private javax.swing.JPanel managemahcines;
    private javax.swing.JButton mngreservation;
    private javax.swing.JButton rent;
    private javax.swing.JButton systemhistory;
    private javax.swing.JPanel systemhistroy;
    // End of variables declaration//GEN-END:variables
}
