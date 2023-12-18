import tkinter as tk
from tkinter import messagebox
import qrcode
import cv2

# Dummy user data (replace this with a secure storage solution in a real application)
user_data = {"user1": "password1", "user2": "password2"}

# Function to generate QR code for the user
def generate_qr_code(username):
    data = f"login:{username}"
    qr = qrcode.QRCode(
        version=1,
        error_correction=qrcode.constants.ERROR_CORRECT_L,
        box_size=10,
        border=4,
    )
    qr.add_data(data)
    qr.make(fit=True)
    img = qr.make_image(fill_color="black", back_color="white")
    img.save(f"{username}_qrcode.png")
    messagebox.showinfo("QR Code Generated", f"QR Code generated for {username}!")

# Function to read QR code from the camera
def read_qr_code():
    cap = cv2.VideoCapture(0)
    while True:
        ret, frame = cap.read()
        if not ret:
            messagebox.showerror("Error", "Failed to access the camera.")
            break

        detector = cv2.QRCodeDetector()
        data, vertices, qr_code = detector.detectAndDecodeMulti(frame)
       
        if data:
            username = data.split(":")[-1]
            if username in user_data:
                messagebox.showinfo("Login Success", f"Login successful for {username}!")
            else:
                messagebox.showerror("Login Failed", "Invalid user.")
            break

        cv2.imshow("QR Code Scanner", frame)
        if cv2.waitKey(1) & 0xFF == ord("q"):
            break

    cap.release()
    cv2.destroyAllWindows()

# GUI setup
root = tk.Tk()
root.title("Secure QR Code Login")

# Label
tk.Label(root, text="Username:").pack(pady=5)

# Entry widget
username_entry = tk.Entry(root)
username_entry.pack(pady=5)

# Button to generate QR code
generate_qr_button = tk.Button(
    root, text="Generate QR Code", command=lambda: generate_qr_code(username_entry.get())
)
generate_qr_button.pack(pady=10)

# Button to read QR code from camera
read_qr_button = tk.Button(root, text="Read QR Code", command=read_qr_code)
read_qr_button.pack(pady=10)

root.mainloop()
