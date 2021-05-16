package exerd.web.ko.main.mapper;

import exerd.web.ko.main.vo.ProductVO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    /** 제품을 삭제 */
    private static String DELETE_PRODUCT = "DELETE FROM " + "exerd.product "
            + "WHERE " + "productSeq = ?";

    /** 제품번호로 제품정보를 조회 */
    private static String SELECT_SERVICEIN_PRODUCT_BY_SEQ = "SELECT "
            + "productName, productVersion, serviceIn, deployDate, remark, insertDate, updateDate, volumePrice, projectPrice "
            + "FROM " + "exerd.product " + "WHERE " + "productSeq = ? ";

    /** 기존 서비스여부 항목의 데이터를 서비스아웃으로 변경 */
    private static String UPDATE_PRODUCT_SERVICEIN_N = "UPDATE "
            + "exerd.product " + "SET  " + "serviceIn = 'n' ";

    /** 제품정보를 수정 */
    private static String UPDATE_PRODUCT = "UPDATE "
            + "exerd.product "
            + "SET  "
            + "productName = ?, productVersion=?, serviceIn=?, remark=?, deployDate=sysdate(), updateDate=sysdate(), volumePrice=?, projectPrice=? "
            + "WHERE " + "productSeq = ?";

    /** 서비스인중인 제품정보조회 */
    private static String SELECT_SERVICEIN_PRODUCT = "SELECT "
            + "productSeq,productName,productVersion,remark,insertDate,updateDate,volumePrice,projectPrice "
            + "FROM " + "exerd.product " + "WHERE " + "serviceIn = ?";

    /** 전체제품정보조회 */
    private static String SELECT_PRODUCT_LIST = "SELECT "
            + "productSeq,productName,productVersion,serviceIn,deployDate,insertDate,updateDate "
            + "FROM " + "exerd.product ";

    /** 제품정보등록 */
    private static String INSERT_PRODUCT = "INSERT INTO exerd.product "
            + "(productName," + "productVersion," + "serviceIn,"
            + "deployDate," + "insertDate," + "updateDate," + "volumePrice,"
            + "projectPrice) " + "VALUES"
            + "(?,?,'y',sysdate(),sysdate(),sysdate(),?,?)";

    /** 서비스인 */
    private static String SERVICE_IN = "Y";

    /**
     * 제품정보를 삭제한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @param productInfo
     *            제품정보<BR>
     */
    public void deleteProductInfo(Connection dbConn, ProductVO productInfo)
            throws Exception {
        System.out.println("deleteProductInfo START");

        PreparedStatement psmt = null;
        try {
            psmt = dbConn.prepareStatement(DELETE_PRODUCT);
            psmt.setInt(1, productInfo.getProductSeq()); // 제품번호
            psmt.execute();

        }
        catch (SQLException e) {
            System.out.println("deleteProductInfo SQL ERROR : "+e);
            throw e;
        }
        finally {
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("deleteProductInfo END");
    }

    /**
     * 제품정보를 수정한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @param productInfo
     *            제품정보<BR>
     */
    public void updateProductInfo(Connection dbConn, ProductVO productInfo)
            throws Exception {
        System.out.println("updateProductInfo START");

        PreparedStatement psmt = null;
        try {
            psmt = dbConn.prepareStatement(UPDATE_PRODUCT);
            psmt.setString(1, productInfo.getProductName()); // 제품명
            psmt.setString(2, productInfo.getProductVersion()); // 제품버전
            psmt.setString(3, productInfo.getServiceIn()); // 서비스여부
            psmt.setString(4, productInfo.getRemark()); // 비고
            psmt.setInt(5, productInfo.getProductVolPrice());// 볼륨 단가
            psmt.setInt(6, productInfo.getProductProPrice());// 프로젝트 단가
            psmt.setInt(7, productInfo.getProductSeq()); // 제품번호

            psmt.execute();

        }
        catch (SQLException e) {
            System.out.println("updateProductInfo SQL ERROR : "+e);
            throw e;
        }
        finally {
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("updateProductInfo END");
    }

    /**
     * 제품번호로 제품정보를 조회한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @param productInfo
     *            제품정보(제품번호)<BR>
     * @return 제품정보<BR>
     */
    public ProductVO searchServiceProductInfoBySeq(Connection dbConn,
                                                   ProductVO productInfo) throws Exception {
        System.out.println("searchServiceProductInfoBySeq START");

        ResultSet result = null;
        PreparedStatement psmt = null;
        ProductVO rstProductInfo = new ProductVO();

        try {
            // SQL검색
            psmt = dbConn.prepareStatement(SELECT_SERVICEIN_PRODUCT_BY_SEQ);
            psmt.setInt(1, productInfo.getProductSeq());

            // SQL결과
            result = psmt.executeQuery();
            while (result.next()) {
                // 검색결과데이터를 추출
                rstProductInfo.setProductSeq(productInfo.getProductSeq());
                rstProductInfo.setProductName(result.getString("productName"));
                rstProductInfo.setProductVersion(result
                        .getString("productVersion"));
                rstProductInfo.setServiceIn(result.getString("serviceIn"));
                rstProductInfo.setRemark(result.getString("remark"));
                rstProductInfo.setInsertDate(result.getDate("insertDate"));
                rstProductInfo.setUpdateDate(result.getDate("updateDate"));
                rstProductInfo.setProductVolPrice(result.getInt("volumePrice"));
                rstProductInfo
                        .setProductProPrice(result.getInt("projectPrice"));
            }

        }
        catch (SQLException e) {
            System.out.println("searchServiceProductInfoBySeq SQL ERROR:"+e);
            throw e;
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("searchServiceProductInfoBySeq END");
        return rstProductInfo;
    }

    /**
     * 서비스중인 제품정보를 조회한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @return 제품정보<BR>
     */
    public ProductVO searchServiceProductInfo(Connection dbConn)
            throws Exception {
        System.out.println("searchServiceProductInfo START");

        ResultSet result = null;
        PreparedStatement psmt = null;
        int resultCount = 0;
        ProductVO rstProductInfo = new ProductVO();

        try {
            // SQL검색
            psmt = dbConn.prepareStatement(SELECT_SERVICEIN_PRODUCT);
            psmt.setString(1, SERVICE_IN);

            // SQL결과
            result = psmt.executeQuery();
            while (result.next()) {
                // 검색결과데이터를 추출
                rstProductInfo.setProductSeq(result.getInt("productSeq"));
                rstProductInfo.setProductName(result.getString("productName"));
                rstProductInfo.setProductVersion(result
                        .getString("productVersion"));
                rstProductInfo.setRemark(result.getString("remark"));
                rstProductInfo.setInsertDate(result.getDate("insertDate"));
                rstProductInfo.setUpdateDate(result.getDate("updateDate"));
                rstProductInfo.setProductVolPrice(result.getInt("volumePrice"));
                rstProductInfo
                        .setProductProPrice(result.getInt("projectPrice"));
                resultCount++;
            }
            System.out.println("productInfo resultCount = " + resultCount);

        }
        catch (SQLException e) {
            System.out.println("searchServiceProductInfo SQL ERROR:"+e);
            throw e;
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("searchServiceProductInfo END");
        return rstProductInfo;
    }

    /**
     * 전체제품정보를 조회한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @return 제품정보리스트<BR>
     */
    public List<ProductVO> searchProductInfoList(Connection dbConn)
            throws Exception {
        System.out.println("searchProductInfoList START");

        List<ProductVO> productInfoList = new ArrayList<ProductVO>();
        ResultSet result = null;
        PreparedStatement psmt = null;
        int resultCount = 0;

        try {
            // SQL검색
            psmt = dbConn.prepareStatement(SELECT_PRODUCT_LIST);

            // SQL결과
            result = psmt.executeQuery();
            while (result.next()) {
                ProductVO rstProductInfo = new ProductVO();
                // 검색결과데이터를 추출
                rstProductInfo.setProductSeq(result.getInt("productSeq"));
                rstProductInfo.setProductName(result.getString("productName"));
                rstProductInfo.setProductVersion(result
                        .getString("productVersion"));
                rstProductInfo.setServiceIn(result.getString("serviceIn"));
                rstProductInfo.setDeployDate(result.getDate("deployDate"));
                rstProductInfo.setInsertDate(result.getDate("insertDate"));
                rstProductInfo.setUpdateDate(result.getDate("updateDate"));

                // list에 등록
                productInfoList.add(rstProductInfo);
                resultCount++;
            }
            System.out.println("productInfoList resultCount = " + resultCount);

        }
        catch (SQLException e) {
            System.out.println("searchProductInfoList SQL ERROR:"+e);
            throw e;
        }
        finally {
            if (result != null) {
                result.close();
            }
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("searchProductInfoList END");
        return productInfoList;
    }

    /**
     * 제품정보를 등록한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     * @param productInfo
     *            제품정보<BR>
     */
    public void insertProductInfo(Connection dbConn, ProductVO productInfo)
            throws Exception {
        System.out.println("insertProductInfo START");

        PreparedStatement psmt = null;
        try {
            psmt = dbConn.prepareStatement(INSERT_PRODUCT);
            psmt.setString(1, productInfo.getProductName()); // 제품명
            psmt.setString(2, productInfo.getProductVersion()); // 제품버전
            psmt.setInt(3, productInfo.getProductVolPrice()); // 볼륨 단가
            psmt.setInt(4, productInfo.getProductProPrice()); // 프로젝트 단가

            psmt.execute();

        }
        catch (SQLException e) {
            System.out.println("insertProductInfo SQL ERROR : "+e);
            throw e;
        }
        finally {
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("insertProductInfo END");
    }

    /**
     * 제품정보의 서비스인 여부를 N으로 수정한다<BR>
     *
     * @param dbConn
     *            DB커넥션<BR>
     */
    public void updateProductInfoServinN(Connection dbConn) throws Exception {
        System.out.println("updateProductInfoServinN START");

        PreparedStatement psmt = null;
        try {
            psmt = dbConn.prepareStatement(UPDATE_PRODUCT_SERVICEIN_N);
            psmt.execute();

        }
        catch (SQLException e) {
            System.out.println("updateProductInfoServinN SQL ERROR : "+e);
            throw e;
        }
        finally {
            if (psmt != null) {
                psmt.close();
            }
        }
        System.out.println("updateProductInfoServinN END");

    }
}
