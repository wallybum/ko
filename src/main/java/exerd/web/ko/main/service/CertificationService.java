package exerd.web.ko.main.service;

import exerd.web.ko.main.mapper.CetificationDao;
import exerd.web.ko.main.util.DBConnector;
import exerd.web.ko.main.vo.CertificationVO;
import exerd.web.ko.main.vo.DownloadMailVO;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;

@Service
public class CertificationService {
    // 현재 사용하는 메서드 아님
    public CertificationVO getCertificationCustomerToOrderNum(String orderNum) {
        CetificationDao certificateDao = new CetificationDao();
        System.out.println("getCertificateCustomer START");
        Connection connection = null;
        CertificationVO certificateInfo = null;
        try {
            connection = DBConnector.newConnection();

            certificateInfo = certificateDao.getCertificationCustomerToOrderNum(connection, orderNum);
        } catch (Exception e) {
            System.out.println("getCertificateCustomer ERROR");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("getCertificateCustomer END");
        return certificateInfo;

    }

    /**
     * @param licenseKey
     * @return certificateInfo
     * DBMS에 접속하여 고객의 라이센스 키를 얻어 옵니다.
     */
    public CertificationVO getCertificationCustomerToLicenseKey(String licenseKey) {
        CetificationDao certificateDao = new CetificationDao();
        System.out.println(" START");
        Connection connection = null;
        CertificationVO certificationVO = null;

        try {
            connection = DBConnector.newConnection();
            certificationVO = certificateDao.getCertificationCustomerToLicenseKey(connection, licenseKey);
        } catch (Exception e) {
            System.out.println("getCertificateCustomerToLicenseKey ERROR");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(" END");
        return certificationVO;

    }

    public boolean hasCustomerInfo(DownloadMailVO mailInfo) {
        CetificationDao certificateDao = new CetificationDao();

        Connection connection = null;
        boolean result = false;
        try {
            connection = DBConnector.newConnection();
            System.out.println("getCertificateCustomer START");
            result = certificateDao.hasCustomerInfo(connection, mailInfo);
        } catch (Exception e) {
            System.out.println("getCertificateCustomer ERROR");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("getCertificateCustomer END");
        return result;

    }

    // 해당 메서드를 사용하는 곳이 Deprecated됨
    public boolean insertSoftwareCertifition(CertificationVO info) {
        CetificationDao dao = new CetificationDao();

        boolean insertResult = false;
        System.out.println("insertSoftwareCertifition START");
        Connection connection = null;
        try {

            connection = DBConnector.newConnection();

            dao.insertSoftwareCertification(connection, info);
            insertResult = true;
        } catch (Exception e) {
            insertResult = false;
            System.out.println("insertSoftwareCertifition ERROR");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("insertSoftwareCertifition END");
        return insertResult;

    }
}
