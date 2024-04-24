package org.example;
import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

import java.util.ArrayList;
import java.util.List;

public class ActiveMQMessageSender {

    private static final String ACTIVEMQ_URL = "tcp://localhost:61616"; // ActiveMQ broker URL
    private static final String TOPIC_NAME = "userIsssues"; // Name of the ActiveMQ topic
    private static final String USERNAME = "admin"; // ActiveMQ username
    private static final String PASSWORD = "admin"; // ActiveMQ password

    public static void main(String[] args) throws JMSException, InterruptedException {
        Session session = null;
        Connection connection = null;
        try {
            connection = null;
            session = null;
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);

            // Create connection and session
            connection = connectionFactory.createConnection(USERNAME, PASSWORD);
            connection.start();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create topic
            Topic topic = session.createTopic(TOPIC_NAME);

            // Create message producer
            MessageProducer producer = session.createProducer(topic);

            // Generate and send messages periodically
            while (true) {
                // Generate your parameters
                String requestId = generateRequestId();
                String[] departments = generateDepartmentList();
                String userIssue = generateUserIssue();

                // Create message with parameters
                TextMessage message = session.createTextMessage();
                message.setStringProperty("RequestId", requestId);
                message.setStringProperty("Departments", String.join(",", departments));
                message.setStringProperty("UserIssue", userIssue);

                // Send message to the topic
                producer.send(message);

                System.out.println("Message sent: RequestId=" + requestId + ", Departments=" + String.join(",", departments) + ", UserIssue=" + userIssue);

                // Sleep for 10 seconds (10000 milliseconds)
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Clean up resources
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Method to generate a random request ID
    private static String generateRequestId() {
        return "REQ" + (int)(Math.random() * 1000); // Example: REQ123
    }

    // Method to generate a random list of departments
    private static String[] generateDepartmentList() {
        List<String> departments = new ArrayList<>();

        // Add 70 department names
        departments.add("HR Department");
        departments.add("IT Department");
        departments.add("Finance Department");
        departments.add("Marketing Department");
        departments.add("Operations Department");
        departments.add("Sales Department");
        departments.add("Customer Support Department");
        departments.add("Research and Development Department");
        departments.add("Legal Department");
        departments.add("Quality Assurance Department");
        departments.add("Product Management Department");
        departments.add("Supply Chain Department");
        departments.add("Logistics Department");
        departments.add("Engineering Department");
        departments.add("Facilities Management Department");
        departments.add("Human Resources Department");
        departments.add("Purchasing Department");
        departments.add("Public Relations Department");
        departments.add("Training Department");
        departments.add("Compliance Department");
        departments.add("Information Security Department");
        departments.add("Business Development Department");
        departments.add("Administration Department");
        departments.add("Project Management Department");
        departments.add("Billing Department");
        departments.add("Accounts Payable Department");
        departments.add("Accounts Receivable Department");
        departments.add("Treasury Department");
        departments.add("Internal Audit Department");
        departments.add("Risk Management Department");
        departments.add("Health and Safety Department");
        departments.add("Environmental Services Department");
        departments.add("Corporate Communications Department");
        departments.add("Investor Relations Department");
        departments.add("Events Management Department");
        departments.add("Client Services Department");
        departments.add("Technical Support Department");
        departments.add("Network Operations Department");
        departments.add("Database Administration Department");
        departments.add("Software Development Department");
        departments.add("Hardware Engineering Department");
        departments.add("Web Development Department");
        departments.add("Mobile Development Department");
        departments.add("Cloud Services Department");
        departments.add("Business Intelligence Department");
        departments.add("Data Analytics Department");
        departments.add("Customer Experience Department");
        departments.add("UX/UI Design Department");
        departments.add("Graphic Design Department");
        departments.add("Content Creation Department");
        departments.add("Media Production Department");
        departments.add("Event Coordination Department");
        departments.add("Regulatory Affairs Department");
        departments.add("Technical Writing Department");
        departments.add("Documentation Department");
        departments.add("Archives Department");
        departments.add("Records Management Department");
        departments.add("Training and Development Department");
        departments.add("Employee Relations Department");
        departments.add("Benefits Administration Department");
        departments.add("Compensation Department");
        departments.add("Recruitment Department");
        departments.add("Talent Acquisition Department");
        departments.add("Learning and Development Department");
        departments.add("Strategic Planning Department");
        departments.add("Business Continuity Department");
        departments.add("Emergency Response Department");
        departments.add("Disaster Recovery Department");
        departments.add("Internal Communications Department");
        return  departments.toArray(new String[departments.size()]);
    }

    // Method to generate a random user issue
    private static String generateUserIssue() {
        List<String> userIssues = new ArrayList<>();

        // Add sample user issues
        userIssues.add("Login issues");
        userIssues.add("Password reset request");
        userIssues.add("Application not responding");
        userIssues.add("File not found error");
        userIssues.add("Email configuration problem");
        userIssues.add("Software installation request");
        userIssues.add("Network connectivity issue");
        userIssues.add("Printer not working");
        userIssues.add("Data access permission problem");
        userIssues.add("System performance degradation");
        userIssues.add("Application access request");
        userIssues.add("Data loss or corruption");
        userIssues.add("Error messages on screen");
        userIssues.add("Security breach concern");
        userIssues.add("Mobile device setup request");
        userIssues.add("VPN connection issue");
        userIssues.add("Website not loading");
        userIssues.add("Database query optimization");
        userIssues.add("Email delivery delay");
        userIssues.add("User interface usability problem");
        userIssues.add("Integration error with third-party system");
        userIssues.add("Payment processing error");
        userIssues.add("Report generation failure");
        userIssues.add("Task automation request");
        userIssues.add("Backup and recovery request");
        userIssues.add("Hardware malfunction");
        userIssues.add("Software bug report");
        userIssues.add("License activation request");
        userIssues.add("Data synchronization issue");
        userIssues.add("Database schema modification request");
        userIssues.add("Audit trail investigation");
        userIssues.add("Workflow approval problem");
        userIssues.add("Data import/export issue");
        userIssues.add("User training request");
        userIssues.add("System configuration change request");
        userIssues.add("Incident escalation");
        userIssues.add("Service outage notification");
        userIssues.add("Dashboard customization request");
        userIssues.add("Data validation error");
        userIssues.add("API integration support");
        userIssues.add("System access control issue");
        userIssues.add("Network bandwidth utilization concern");
        userIssues.add("Application version upgrade request");
        userIssues.add("Code deployment failure");
        userIssues.add("Environment provisioning request");
        userIssues.add("Documentation update request");
        userIssues.add("System health monitoring alert");
        userIssues.add("User account provisioning");
        userIssues.add("Service level agreement (SLA) inquiry");
        userIssues.add("Compliance violation report");
        userIssues.add("Data archival and retention request");
        userIssues.add("Error log analysis");


        return userIssues.get((int)(Math.random() * userIssues.size()));
}}
