package project.model;

public class Customer {
        private String id;
        private String name;
        private String gender;
        private String address;
        private String phone;
        private String status;
        private String email;
        private String information;
        private int totalAmountPaid;

        public final double M1=10000000;
        public final double M2=20000000;
        public final double M3=50000000;

        public Customer() {
        }

        public Customer(String id, String name, String gender, String address, String phone, String status, String email, String information, int totalAmountPaid) {
            this.id=id;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.phone = phone;
            this.status = status;
            this.email = email;
            this.information = information;
            this.totalAmountPaid = totalAmountPaid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
                return name;
            }

        public void setName(String name) {
                this.name = name;
            }
        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getInformation() {
            return information;
        }

        public void setInformation(String information) {
            this.information = information;
        }

        public int getTotalAmountPaid() {
            return totalAmountPaid;
        }

        public void setTotalAmountPaid(int totalAmountPaid) {
            this.totalAmountPaid = totalAmountPaid;
            if (totalAmountPaid<M1) status="Đồng";
            if (totalAmountPaid>=M1 && totalAmountPaid<M2) status="Bạc";
            if (totalAmountPaid>=M2 && totalAmountPaid <M3) status="Vàng";
            if (totalAmountPaid>=M3) status="Kim Cương";
        }
    @Override
    public String toString() {
        return "KhachHang{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", email='" + email + '\'' +
                ", information='" + information + '\'' +
                ", totalAmountPaid=" + totalAmountPaid +
                '}';
    }

    public int getDiscount() {
            int discount = 0;
            if (totalAmountPaid<M1) discount=1;
            if (totalAmountPaid>=M1 && totalAmountPaid<M2) discount=2;
            if (totalAmountPaid>=M2 && totalAmountPaid <M3) discount=5;
            if (totalAmountPaid>=M3) discount=7;
            return discount;
        }


}
