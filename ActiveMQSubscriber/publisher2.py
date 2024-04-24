import time
import random
import stomp

# Define ActiveMQ broker connection details
host = 'localhost'
port = 61613
username = 'admin'
password = 'admin'
topic_name = '/topic/userIssues'

# Predefined lists of departments and user issues
departments = [
    "HR Department", "IT Department", "Finance Department", 
    "Marketing Department", "Operations Department", "Sales Department",
    "Customer Support Department", "Research and Development Department",
    "Legal Department", "Quality Assurance Department", "Product Management Department",
    "Supply Chain Department", "Logistics Department", "Engineering Department",
    "Facilities Management Department", "Human Resources Department", "Purchasing Department",
    "Public Relations Department", "Training Department", "Compliance Department",
    "Information Security Department", "Business Development Department", "Administration Department",
    "Project Management Department", "Billing Department", "Accounts Payable Department",
    "Accounts Receivable Department", "Treasury Department", "Internal Audit Department",
    "Risk Management Department", "Health and Safety Department", "Environmental Services Department",
    "Corporate Communications Department", "Investor Relations Department", "Events Management Department",
    "Client Services Department", "Technical Support Department", "Network Operations Department",
    "Database Administration Department", "Software Development Department", "Hardware Engineering Department",
    "Web Development Department", "Mobile Development Department", "Cloud Services Department",
    "Business Intelligence Department", "Data Analytics Department", "Customer Experience Department",
    "UX/UI Design Department", "Graphic Design Department", "Content Creation Department",
    "Media Production Department", "Event Coordination Department", "Regulatory Affairs Department",
    "Technical Writing Department", "Documentation Department", "Archives Department",
    "Records Management Department", "Training and Development Department", "Employee Relations Department",
    "Benefits Administration Department", "Compensation Department", "Recruitment Department",
    "Talent Acquisition Department", "Learning and Development Department", "Strategic Planning Department",
    "Business Continuity Department", "Emergency Response Department", "Disaster Recovery Department",
    "Internal Communications Department"
]

user_issues = [
    "Login issues", "Password reset request", "Application not responding",
    "File not found error", "Email configuration problem", "Software installation request",
    "Network connectivity issue", "Printer not working", "Data access permission problem",
    "System performance degradation", "Application access request", "Data loss or corruption",
    "Error messages on screen", "Security breach concern", "Mobile device setup request",
    "VPN connection issue", "Website not loading", "Database query optimization", "Email delivery delay",
    "User interface usability problem", "Integration error with third-party system", "Payment processing error",
    "Report generation failure", "Task automation request", "Backup and recovery request", "Hardware malfunction",
    "Software bug report", "License activation request", "Data synchronization issue",
    "Database schema modification request", "Audit trail investigation", "Workflow approval problem",
    "Data import/export issue", "User training request", "System configuration change request",
    "Incident escalation", "Service outage notification", "Dashboard customization request",
    "Data validation error", "API integration support", "System access control issue",
    "Network bandwidth utilization concern", "Application version upgrade request", "Code deployment failure",
    "Environment provisioning request", "Documentation update request", "System health monitoring alert",
    "User account provisioning", "Service level agreement (SLA) inquiry", "Compliance violation report",
    "Data archival and retention request", "Error log analysis"
]

# Create a connection to the ActiveMQ broker
conn = stomp.Connection([(host, port)])

def send_message():
    try:
        conn.connect(username=username, password=password)
        while True:
            # Generate your parameters
            request_id = f"REQ{int(time.time() * 1000)}"
            selected_departments = random.sample(departments, k=random.randint(1, 5))  # Randomly choose 1 to 5 departments
            user_issue = random.choice(user_issues)  # Randomly choose a user issue

            # Create message with parameters
            message = f"RequestId={request_id}, Departments={','.join(selected_departments)}, UserIssue={user_issue}"

            # Send message to the topic
            conn.send(body=message, destination=topic_name)
            print(f"Message sent: {message}")

            # Sleep for 10 seconds (10000 milliseconds)
            time.sleep(0.5)

    except KeyboardInterrupt:
        print("Interrupted")
    except Exception as e:
        print(f"Error: {e}")
    finally:
        # Disconnect from the broker
        conn.disconnect()

if __name__ == "__main__":
    # Run the message sender
    send_message()
