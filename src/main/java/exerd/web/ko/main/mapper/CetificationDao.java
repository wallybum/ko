package exerd.web.ko.main.mapper;

import exerd.web.ko.main.vo.CertificationVO;
import exerd.web.ko.main.vo.DownloadMailVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CetificationDao {
    private static String SELECT_CERTIFICATION_CUSTOMER_TO_ORDERNUM = "SELECT pay.name,pay.address,pay.licenseKey,pay.quantity,pay.receiverEmail,"
            + "cert.orderNum,pay.licenseKeyGenDate,cert.documentNo,pd.productVersion,pay.telephone "
            + "FROM exerd.certificate as cert, exerd.payment as pay, exerd.product as pd "
            + "WHERE pay.orderNum = cert.orderNum and pd.productSeq = pay.productSeq and cert.documentNo = ?";

    private static String SELECT_CERTIFICATION_CUSTOMER_TO_LICENSEKEY = "SELECT pay.orderNum,pay.name,pay.address,pay.licenseKey,pay.quantity,pay.receiverEmail,"
            + " pay.licenseKeyGenDate,pd.productVersion,pay.newOrderNum, pay.telephone " + "FROM exerd.payment as pay, exerd.product as pd " + "WHERE pd.productSeq = pay.productSeq and pay.licenseKey = ?";

    private static String SELECT_CUSTOMER_INFO = "SELECT pay.name, pay.email, prod.productVersion " + "FROM exerd.payment as pay, exerd.product as prod "
            + "WHERE (pay.email = ?  OR pay.name=?) " + "AND pay.productSeq = prod.productSeq " + "AND prod.productVersion LIKE '{$version}.%'";

    @Deprecated
    private static String INSERT_SOFTWARE_CERTIFICATION = "INSERT INTO exerd.software_certification(orderNum , regist) VALUES(?,sysdate())";

    public CertificationVO getCertificationCustomerToOrderNum(Connection dbConn, String orderNum) throws Exception {
        ResultSet result = null;
        PreparedStatement psmt = null;
        CertificationVO certificationInfo = null;

        try {
            psmt = dbConn.prepareStatement(SELECT_CERTIFICATION_CUSTOMER_TO_ORDERNUM);
            psmt.setString(1, orderNum);

            result = psmt.executeQuery();
            while (result.next()) {
                certificationInfo = new CertificationVO();
                certificationInfo.setOrderNum(result.getString("orderNum"));
                certificationInfo.setDocumentNo(result.getString("documentNo"));
                certificationInfo.setName(result.getString("name"));
                certificationInfo.setEmail(result.getString("receiverEmail"));
                certificationInfo.setAddress(result.getString("address"));
                certificationInfo.setLicenseKeyGenDate(result.getString("licenseKeyGenDate"));
                certificationInfo.setProductVersion(result.getString("productVersion"));
                certificationInfo.setQuantity(result.getInt("quantity"));
                certificationInfo.setLicenseKey(result.getString("licenseKey"));
                certificationInfo.setTelephone(result.getString("telephone"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        return certificationInfo;

    }

    /**
     * @param dbConn
     * @param licenseKey
     * @return certificationVO
     * @throws Exception
     * 쿼리 질의 결과를 certificationVO 객체 담아 리턴합니다.
     */
    public CertificationVO getCertificationCustomerToLicenseKey(Connection dbConn, String licenseKey) throws Exception {
        ResultSet result = null;
        PreparedStatement psmt = null;
        CertificationVO certificationVO = null;

        try {
            psmt = dbConn.prepareStatement(SELECT_CERTIFICATION_CUSTOMER_TO_LICENSEKEY);
            psmt.setString(1, licenseKey);

            result = psmt.executeQuery();
            while (result.next()) {
                certificationVO = new CertificationVO();
                certificationVO.setOrderNum(result.getString("orderNum"));
                certificationVO.setName(result.getString("name"));
                certificationVO.setEmail(result.getString("receiverEmail"));
                certificationVO.setAddress(result.getString("address"));
                certificationVO.setLicenseKeyGenDate(result.getString("licenseKeyGenDate"));
                certificationVO.setQuantity(result.getInt("quantity"));
                certificationVO.setLicenseKey(result.getString("licenseKey"));
                certificationVO.setProductVersion(result.getString("productVersion"));
                certificationVO.setTelephone(result.getString("telephone"));
                certificationVO.setNewOrderNum(result.getString("newOrderNum"));
            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        return certificationVO;

    }

    public boolean hasCustomerInfo(Connection dbConn, DownloadMailVO mailInfo) throws Exception {
        ResultSet result = null;
        PreparedStatement psmt = null;
        String resultName = null;
        try {
            psmt = dbConn.prepareStatement(replaceVersion(SELECT_CUSTOMER_INFO, mailInfo.getVersion()));
            psmt.setString(1, mailInfo.getMail());
            psmt.setString(2, mailInfo.getCompany());

            result = psmt.executeQuery();
            while (result.next()) {

                resultName = result.getString("name");

            }

        } catch (SQLException e) {
            throw e;
        } finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        return resultName != null ? true : false;

    }

    public void insertSoftwareCertification(Connection dbConn, CertificationVO info) throws Exception {
        PreparedStatement psmt = null;
        try {
            psmt = dbConn.prepareStatement(INSERT_SOFTWARE_CERTIFICATION);
            psmt.setString(1, info.getOrderNum()); // 주문번호

            psmt.execute();

        } catch (SQLException e) {
            throw e;
        } finally {
            if (psmt != null) {
                psmt.close();
            }
        }
    }

    private String replaceVersion(String query, String version) {
        return query.replace("{$version}", "version");
    }
}