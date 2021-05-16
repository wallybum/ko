package exerd.web.ko.main.vo;

//@Getter
//@Setter
//@ToString
//public class CertificationVO {
//    private String documentNo;
//    private String orderNum;
//    private String publishDate;
//    private int idx;
//    private String name;
//    private String email;
//    private String address;
//    private String licenseKeyGenDate;
//    private int quantity;
//    private String productVersion;
//    private String licenseKey;
//    private String telephone;
//    private String newOrderNum;
//}

public class CertificationVO {
    private String documentNo;
    private String orderNum;
    private String publishDate;
    private int idx;
    private String name;
    private String email;
    private String address;
    private String licenseKeyGenDate;
    private int quantity;
    private String productVersion;
    private String licenseKey;
    private String telephone;
    private String newOrderNum;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the documentNo
     */
    public String getDocumentNo() {
        return documentNo;
    }

    /**
     * @param documentNo
     *            the documentNo to set
     */
    public void setDocumentNo(String documentNo) {
        this.documentNo = documentNo;
    }

    /**
     * @return the orderNum
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * @param orderNum
     *            the orderNum to set
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * @return the publishDate
     */
    public String getPublishDate() {
        return publishDate;
    }

    /**
     * @param publishDate
     *            the publishDate to set
     */
    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    /**
     * @return the idx
     */
    public int getIdx() {
        return idx;
    }

    /**
     * @param idx
     *            the idx to set
     */
    public void setIdx(int idx) {
        this.idx = idx;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the licenseKeyGenDate
     */
    public String getLicenseKeyGenDate() {
        return licenseKeyGenDate;
    }

    /**
     * @param licenseKeyGenDate
     *            the licenseKeyGenDate to set
     */
    public void setLicenseKeyGenDate(String licenseKeyGenDate) {
        this.licenseKeyGenDate = licenseKeyGenDate;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param i
     *            the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the productVersion
     */
    public String getProductVersion() {
        return productVersion;
    }

    /**
     * @param productVersion
     *            the productVersion to set
     */
    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getNewOrderNum() {
        return newOrderNum;
    }

    public void setNewOrderNum(String newOrderNum) {
        this.newOrderNum = newOrderNum;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "CertificateInfo [documentNo=" + documentNo + ", orderNum=" + orderNum + ", publishDate=" + publishDate + ", idx=" + idx + ", name=" + name
                + ", email=" + email + ", address=" + address + ", licenseKeyGenDate=" + licenseKeyGenDate + ", quantity=" + quantity + ", productVersion="
                + productVersion + "]";
    }

    public String getLicenseKey() {
        String tmp = licenseKey.substring(0, 4);
        return tmp;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }
}
