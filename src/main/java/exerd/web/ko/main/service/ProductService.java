package exerd.web.ko.main.service;

import exerd.web.ko.main.mapper.ProductDAO;
import exerd.web.ko.main.util.DBConnector;
import exerd.web.ko.main.vo.ProductVO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    /**
     * 제품정보를 삭제한다<BR>
     *
     * @param productInfo
     *            제품정보<BR>
     */
    public void deleteProductInfo(ProductVO productInfo) {
        System.out.println("deleteProductInfo START");

        // 제품정보DAO
        ProductDAO productDao = new ProductDAO();

        Connection connection = null;

        try {
            connection = DBConnector.newConnection();

            // 제품정보를 삭제한다
            productDao.deleteProductInfo(connection, productInfo);

        } catch (Exception e) {
            System.out.println("deleteProductInfo SERVICE ERROR:"+ e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("updateProductInfo END");
    }

    /**
     * 제품정보를 수정한다<BR>
     *
     * @param productInfo
     *            제품정보<BR>
     */
    public void updateProductInfo(ProductVO productInfo) {
        System.out.println("updateProductInfo START");

        // 제품정보DAO
        ProductDAO productDao = new ProductDAO();
        Connection connection = null;

        try {
            connection = DBConnector.newConnection();
            // 트랜젝션을 시작
            connection.setAutoCommit(false);

            if ("y".equals(productInfo.getServiceIn())) {
                // 기존데이터의 서비스유무를 n로 수정
                productDao.updateProductInfoServinN(connection);
            }
            // 제품정보를 수정
            productDao.updateProductInfo(connection, productInfo);
            connection.commit();

        } catch (Exception e) {
            System.out.println("updateProductInfo SERVICE ERROR:"+ e);

        } finally {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    System.out.println("updateProductInfo SERVICE ERROR(TX):"+e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("updateProductInfo END");
    }

    /**
     * 현재 지원하는 eXERD 버전 정보를 리턴합니다.(3.x)
     * @return
     */
    public ProductVO searchServiceProductInfo() {
        System.out.println("searchServiceProductInfo START");

        // 제품 정보 조회 DAO
        ProductDAO productDao = new ProductDAO();
        // 제품정보
        ProductVO productInfo = new ProductVO();

        Connection connection = null;

        try {
            connection = DBConnector.newConnection();

            // 서비스중인 제품 정보 리스트 취득 (현재 3.x)만 지원
            productInfo = productDao.searchServiceProductInfo(connection);
        } catch (Exception e) {
            System.out.println("searchProductInfo SERVICE ERROR:"+ e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("searchServiceProductInfo END");
        return productInfo;
    }

    /**
     * 제품정보를 취득<BR>
     *
     * @param productInfo
     *            제품정보<BR>
     * @return 제품정보<BR>
     */
    public ProductVO searchServiceProductInfoBySeq(ProductVO productInfo) {
        System.out.println("searchServiceProductInfoBySeq START");

        // 제품정보조회DAO
        ProductDAO productDao = new ProductDAO();
        // 제품정보
        ProductVO rstProductInfo = new ProductVO();

        Connection connection = null;

        try {
            connection = DBConnector.newConnection();

            // 서비스중인 제품정보를 취득
            rstProductInfo = productDao.searchServiceProductInfoBySeq(connection, productInfo);
        } catch (Exception e) {
            System.out.println("searchServiceProductInfoBySeq SERVICE ERROR:"+e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("searchServiceProductInfoBySeq END");
        return rstProductInfo;
    }

    /**
     * 제품정보리스트를 취득<BR>
     *
     * @return 제품정보리스트<BR>
     */
    public List<ProductVO> searchProductInfoList() {
        System.out.println("searchProductInfoList START");

        // 제품정보조회DAO
        ProductDAO productDao = new ProductDAO();
        List<ProductVO> productInfoList = new ArrayList<ProductVO>();

        Connection connection = null;

        try {
            connection = DBConnector.newConnection();

            // 서비스중인 제품정보리스트 취득
            productInfoList = productDao.searchProductInfoList(connection);
        } catch (Exception e) {
            System.out.println("searchProductInfoList SERVICE ERROR:"+ e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("searchProductInfoList END");
        return productInfoList;
    }

    /**
     * 제품정보리스트를 취득<BR>
     *
     * <BR>
     */
    public void insertProductInfo(ProductVO productInfo) {
        System.out.println("insertProductInfo START");

        // 제품정보조회DAO
        ProductDAO productDao = new ProductDAO();
        Connection connection = null;

        try {

            connection = DBConnector.newConnection();
            // 트랜젝션을 시작
            connection.setAutoCommit(false);
            // 기존데이터의 서비스유무를 n로 수정
            productDao.updateProductInfoServinN(connection);
            // 제품정보를 등록
            productDao.insertProductInfo(connection, productInfo);
            connection.commit();

        } catch (Exception e) {
            System.out.println("insertProductInfo SERVICE ERROR:"+e);
        } finally {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e) {
                    System.out.println("insertProductInfo SERVICE ERROR(TX):"+ e);
                }
            }

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("insertProductInfo END");
    }
}
