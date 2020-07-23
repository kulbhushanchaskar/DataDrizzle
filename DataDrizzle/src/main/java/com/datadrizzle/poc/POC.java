package com.datadrizzle.poc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.teiid.adminapi.Admin;
import org.teiid.adminapi.jboss.AdminFactory;
import org.teiid.webui.share.services.ITeiidService;

public class POC {

    /*static String host = "qualidiqa";
    static String port = "31000";
    static String vdb = "SvcSourceVdb_flat_file_conn1";
    static String modelName = "flat_file_conn1";
    static String pwd = "user@123";
    private static Admin admin;*/
    
/*    static String host = "10.110.250.147";
    static String port = "31000";
    static String vdb = "SvcSourceVdb_sql_teju";
    static String modelName = "sql_teju";
    static String pwd = "user@123";
    private static Admin admin;*/
    
    /*static String host = "localhost";
    static String port = "31000";
    static String vdb = "combine4";
    static String modelName = "MysqlXADS2";
    static String pwd = "user@123";
    private static Admin admin;*/
	
	static String host = "localhost";
    static String port = "31000";
    static String vdb = "combine";
    static String modelName = "MysqlXADS3";
    static String pwd = "user@123";
    private static Admin admin;
    
    @Autowired
    ITeiidService teiidService;

    public static void main(String[] args) throws Exception {
//        createMongoResourceAdapter();
         executeSelect();
//        executeWebServiceSelect();
//    	getSystemJNDIInfo();
    	
    	/*POC poc = new POC();
    	poc.testConnection();*/
        
        
        /*admin = AdminFactory.getInstance().createAdmin("10.10.127.53", 9990, "admin", "admin".toCharArray());
        
        String schema = admin.getSchema(vdb, 1, modelName, null, null);
        
        System.out.println("schema "+schema);*/
    	
    	/*DataSourceWithVdbDetailsBean dbVDBBean = new DataSourceWithVdbDetailsBean();
    	dbVDBBean.setCreatedBy("DataPlayGround");
    	
    	dbVDBBean.setSourceVdbName("SVC_MySQLConnection");
    	dbVDBBean.setTranslator("mysql5");
    	dbVDBBean.setJndiName("java:/MySQLDS");
    	
//    	List<DataSourceDetailsBean> dsPropList = new ArrayList<>();
    	DataSourceDetailsBean dsDetailsBean = new DataSourceDetailsBean();
    	dsDetailsBean.setCreatedBy("DataPlayGround");
    	dsDetailsBean.setVersion("1");
    	List<DataSourcePropertyBean> dsPropBeanList = new ArrayList<>(); 
//    	DataSourcePropertyBean dsPropBean = new DataSourcePropertyBean();
//    	dsPropBean.setName("");
//    	dsPropBean.setValue("");
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("").setValue(""));
    	
    	dbVDBBean.setProperties(dsPropBeanList);*/
    	
//    	TranslatorImportPropertyBean translatorImportBean = new TranslatorImportPropertyBean();
    	
    	/*dbVDBBean.setImportProperties(properties);
    	public void setImportProperties(List<TranslatorImportPropertyBean> properties) {
    		this.importProperties = properties;
    	}*/
    	
    	
    }
    
    public void testConnection() {
    	
    }
    
    public static void getSystemJNDIInfo() throws NamingException {
		InitialContext ctx = new InitialContext();
		NamingEnumeration<NameClassPair> list = ctx.list("java:comp/env/jdbc");
		while (list.hasMore()) {
			System.out.println(list.next().getName());
		}
    }
    
    private static void createMongoResourceAdapter() throws Exception {
        admin = AdminFactory.getInstance().createAdmin("10.10.127.53", 9990, "admin", "admin".toCharArray());

        String deployedName = "mongo12345";

        /*
         * {enabled=true, password=testpass123, use-java-context=true, user-name=root, 
         * connection-url=jdbc:mysql://gl457:3306/aakash_test, max-pool-size=20, 
         * jndi-name=java:/TESTDSMYSQL20171215115511782}
         */
        Properties p = new Properties();
//        p.setProperty("main", "org.teiid.resource.adapter.mongodb.MongoDBManagedConnectionFactory");
        p.setProperty("class-name", "org.teiid.resource.adapter.mongodb.MongoDBManagedConnectionFactory");
//        p.setProperty("transaction-support", "NoTransaction");
//        p.setProperty("transaction-support", "NoTransaction");
//        p.setProperty("enabled", "true");
//        p.setProperty("jndi-name", "java:/mongoDS1");
        p.setProperty("Database", "mongoTest");
        p.setProperty("RemoteServerList", "10.110.250.147:27019");
//        p.setProperty("Username", "");
//        p.setProperty("Password", "");
        
        admin.createDataSource(deployedName, "mongodb", p);
        
        System.out.println("datasource created");
    }

    private static void executeWebServiceSelect() throws Exception {
        Connection connection = getDriverConnection();
        // String select_par_sql = "SELECT * FROM
        // mysqlKul.testschema.test_table_for_dg";
        /*
         * String select_par_sql =
         * "SELECT * FROM mysqlKul.testschema.test_table_for_dg_temp " +
         * "WHERE TRIM(name) IN(fullname, title);";
         */
        // String sqlQuery = "select * from sys.columns";
        // String select_par_sql = "SELECT count(*) from mkt";
        /*String select_par_sql = "SELECT PLANT.* from (call plantWs.invokeHttp(action=>'GET')) f, "+  
" XMLTABLE('/CATALOG/PLANT' PASSING XMLPARSE(DOCUMENT f.result WELLFORMED) COLUMNS   "+
 "   COMMON string PATH 'COMMON',  "+
  "  BOTANICAL string  PATH 'BOTANICAL') AS PLANT  ";*/
        
        /*String select_par_sql = "SELECT PLANT.* from (exec invokeHttp(action=>'GET')) f, "+  
                " XMLTABLE('/CATALOG/PLANT' PASSING XMLPARSE(DOCUMENT f.result WELLFORMED) COLUMNS   "+
                 "   COMMON string PATH 'COMMON',  "+
                  "  BOTANICAL string  PATH 'BOTANICAL') AS PLANT  ";*/
        
        
        /*String select_par_sql = "SELECT name,age  "+
"FROM (EXEC invokeHttp(action =>  'GET', "+
"                                               contentType =>  'TRUE'"+
 "                                             )"+
  "        ) AS f, "+
   "       XMLTABLE('result/result' PASSING JSONTOXML('result', f.result) COLUMNS name string PATH 'name', age integer PATH 'age') AS t where name = 'John'";*/
        
        String select_par_sql = "SELECT "+
          "strat "+
        "FROM "+
         " ( "+
          "  EXEC invokehttp(action = > 'GET', contenttype = > 'TRUE') "+
          ") AS f,"+
          "xmltable( "+
          "  'result/result' passing jsontoxml('result', f.result) columns strat String path 'strat' "+
          ") AS API_TABLE ";
        

        /*String select_par_sql = "SELECT count(*) FROM file_con.SampleSourceFile WHERE InvAssetType not in(SELECT name "+
                "FROM (EXEC invokeHttp(action =>  'GET', "+
                "                                               contentType =>  'TRUE'"+
                 "                                             )"+
                  "        ) AS f, "+
                   "       XMLTABLE('result/result' PASSING JSONTOXML('result', f.result) COLUMNS name string PATH 'NAME', age integer PATH 'age') AS t where name = 'John')";*/
        
        /*String select_par_sql = "SELECT strat FROM   (EXEC invokehttp(action => 'GET', contenttype => 'TRUE' ) ) AS f, "+
        "xmltable('result/result' passing jsontoxml('result', f.result) columns strat string path 'strat') AS t";*/

//        select_par_sql = "SELECT InvAssetType FROM file_con.SampleSourceFile WHERE InvAssetType not in('xyz')";
        
        //-------
        
        /*select_par_sql = "SELECT status AS id "+
                "FROM (EXEC invokeHttp(action          =>  'GET', "+
                "                                               contentType =>  'TRUE'"+
                 "                                             )"+
                  "        ) AS f, "+
                   "       XMLTABLE('/status' PASSING JSONTOXML('data', f.result) COLUMNS status string PATH 'status') AS t";*/
        
        /*String select_par_sql = "exec invokeHttp(action=>'GET')";
        CallableStatement cStmt = connection.prepareCall(select_par_sql);
        cStmt.execute();
        javax.sql.rowset.serial.SerialBlob blob = (javax.sql.rowset.serial.SerialBlob) cStmt.getObject(1);
        
        byte[] bdata = blob.getBytes(1, (int) blob.length());
        String s = new String(bdata);
        System.out.println(s);*/
        
        /*
         * String select_par_sql =
         * "SELECT * FROM mysqlKul.testschema.test_table_for_dg_temp WHERE " +
         * "LTRIM(name)IN(fullname, title)";
         */
        
//        select_par_sql = "SELECT tablename from sys.columns where schemaname='"+connName+"' limit 1","SvcSourceVdb_"+connName";

        execute(connection, select_par_sql);
    }

    private static void executeSelect() throws Exception {
        Connection connection = getDriverConnection();
        // String select_par_sql = "SELECT * FROM
        // mysqlKul.testschema.test_table_for_dg";
        /*
         * String select_par_sql =
         * "SELECT * FROM mysqlKul.testschema.test_table_for_dg_temp " +
         * "WHERE TRIM(name) IN(fullname, title);";
         */
        // String sqlQuery = "select * from sys.columns";
        // String select_par_sql = "SELECT count(*) from mkt";
        /*
         * String select_par_sql =
         * "SELECT * FROM mysqlKul.testschema.test_table_for_dg_temp WHERE " +
         * "LTRIM(name)IN(fullname, title)";
         */
        // [B@23444a36
        // String select_par_sql = "SELECT AES_ENCRYPT(name, '11223') from
        // addresscp";
        // String select_par_sql = "SELECT count(name) FROM addresscp a";
        // String select_par_sql = "INSERT INTO ACCOUNT VALUES(
        // AES_ENCRYPT('145034038510','1234'), 12.44)";
        // String select_par_sql = "SELECT name from addresscp where name =
        // 'cereal''s'";
        // String select_par_sql = "SELECT * from addresscp where name =
        // 'Sihar\\\\\\-e'";
        // SvcSourceVdb_mysqlconn
        // String select_par_sql = "INSERT INTO
        // mysqlconn.aakash_test.\"TEST\"(mysqlconn.aakash_test.\"TEST\".\"name\")VALUES(CalcIncome(1000))";
        // String select_par_sql = "SELECT * FROM TEST";
        // String select_par_sql = "select
        // funConn.SQLTOOLKIT.ADMIN.ENCRYPT('abc','abc')";
        // String select_par_sql = "SELECT CalcIncome(1000)";
        // String select_par_sql = "SELECT ENCRYPT('name', '11223')";
        // String select_par_sql = "SELECT AES_ENCRYPT('name', '11223')";
        // String select_par_sql = "INSERT INTO ADMIN.TEST_TBL VALUES(?,?)";
        // String select_par_sql = "SELECT
        // isBusinessHours(netezzConn.TDM.ADMIN.REGION_DM.DW_CREATE_DTM) FROM
        // netezzConn.TDM.ADMIN.REGION_DM LIMIT 1";
        // String select_par_sql = "SELECT
        // netezzConn.TDM.ADMIN.REGION_DM.DW_CREATE_DTM FROM
        // netezzConn.TDM.ADMIN.REGION_DM";
        // String select_par_sql = "SELECT isBusinessHours('1900-01-01
        // 11:45:00.0')";

        /*
         * String select_par_sql =
         * "INSERT INTO conNetezza.TDM_BV.ADMIN.CORP_DM VALUES(1, " +
         * "'c', isBusinessHours('2017-05-07 12:01:13'), '2011-05-14 20:42:49', 'cmietl', ' 2011-05-14 20:42:49', 1)"
         * ;
         */
        String select_par_sql = "SELECT *  FROM mysqlconn.aakash_test.account";

        // execute(connection, select_par_sql);

        select_par_sql = "INSERT INTO mysqlconn.aakash_test.test VALUES(AES_ENCRYPT('1','plesuer'))";

        // String select_par_sql = "SELECT CalcIncome(1,2)";
        // execute(connection, select_par_sql);
        select_par_sql = "SELECT AES_ENCRYPT('1','plesuer')";

        select_par_sql = "SELECT names_2014.name, names_2014.count FROM tdm_dataset.names_2014 LIMIT 1";

        select_par_sql = "INSERT INTO tdm_dataset.names_2014(name,gender,count) VALUES('tdm8','M','16')";

        select_par_sql = "SELECT *  FROM mysqlKul.aakash_test.account";

        /*
         * select_par_sql =
         * "SELECT max(AGENT_LICENSE_DIM.AGENT_LICENSE_NO),AGENT.A_SSN_NO"+
         * " FROM AGENT"+ " LEFT JOIN"+
         * " (SELECT (AGENT_LICENSE_NO) AS AGENT_LICENSE_NO,AGENT_TIN_ID  FROM (SELECT AGENT_LICENSE_NO,AGENT_TIN_ID FROM AGENT_LICENSE_DIM  WHERE AGENT_LICENSE_TYPE = 'N' ORDER BY AGENT_LICENSE_STATE_CD DESC,AGENT_LICENSE_EFFECTIVE_DT DESC ) LICENSE_NO GROUP BY AGENT_TIN_ID) AGENT_LICENSE_DIM"
         * + " ON"+ " AGENT_LICENSE_DIM.AGENT_TIN_ID=AGENT.A_SSN_NO"+
         * " GROUP BY"+ " AGENT.A_SSN_NO";
         */

//         select_par_sql = "INSERT INTO Customer(\"_id\", FirstName,LastName) VALUES (56, 'kul', 'bhushan')";
//        select_par_sql = "SELECT * from Customer";
//        select_par_sql = "INSERT INTO mapreducecoll(post_text,user_name,status) VALUES ('post', 'kul', 'bhushan')";

        /*select_par_sql = "SELECT * from xmltable('/dept/employee' PASSING convert('<dept bldg=\"101\"> "
                + " <employee id=\"901\">  " + "    <name>  " + "       <first>John</first>"
                + "      <last>Doe</last>  " + " </name>  " + "<office>344</office>"
                + "<salary currency=\"USD\">55000</salary>" + "</employee>  " + "<employee id=\"902\">" + "   <name>  "
                + "      <first>Peter</first>" + "     <last>Pan</last>  " + " </name>  " + " <office>216</office>"
                + " <phone>905-416-5004</phone>" + " </employee>  " + "</dept>', xml) COLUMNS"
                + "  empID     integer     PATH '@id'," + " firstname     string     PATH 'name/first',"
                + "lastname     string     PATH 'name/last') AS employee";*/
        
        //from local file system...
        /*select_par_sql = "SELECT employee.* from (call department.getTextFiles('*.xml')) f,"+   
   "xmltable('/dept/employee' PASSING xmlparse(DOCUMENT f.file) COLUMNS "+   
       "empID     integer     PATH '@id',  "+
       "firstname     string     PATH 'name/first',"+  
       "lastname     string     PATH 'name/last', "+
       "office string path 'office') AS employee";*/
         
         /*select_par_sql = "call saveFile('department.xml',"
                 + "cast('<roster>"
                 + "<student age=\"18\">AB</student>"
                 + "<student age=\"23\">BC</student>"
                 + "<student>NOAGE</student>"
                 + "</roster>' as XML)"
                 + ")";*/
        /* select_par_sql = "SELECT employee.* from ("+  
   "call departmentWS.invoke(request=>'<teiid:getdepartmentWS xmlns:teiid=\"http://teiid.org\"/>',"+  
    "                      binding='HTTP')) f,"+   
   "xmltable(XMLNAMESPACES('http://teiid.org' as \"teiid\"),"+   
    "  '/teiid:getdepartmentWSResponse/return/employee' PASSING f.result COLUMNS "+   
       "empID     integer    PATH '@id',  "+
       "firstname string     PATH 'name/first',"+  
       "lastname  string     PATH 'name/last', office string path 'office') AS employee";  */
         
         /*select_par_sql = "SELECT PLANT.* from ( "+
                   "call departmentWS.invoke(request=>'<teiid:getdepartmentWS xmlns:teiid=\"http://teiid.org\"/>',"+  
                    "binding='HTTP')) f,   "+
                   "xmltable(XMLNAMESPACES('http://teiid.org' as \"teiid\"), "+   
                    "  '/teiid:getdepartmentWSResponse/return/PLANT' PASSING f.result COLUMNS "+   
                    "  COMMON string     PATH 'COMMON', "+  
                    "  BOTANICAL  string     PATH 'BOTANICAL') AS PLANT";*/
        
        /*select_par_sql = "SELECT PLANT.* from (call plantWS.invoke(request=>'<teiid:getplantWS xmlns:teiid=\"http://teiid.org\"/>',binding='HTTP')) f,"+   
                "xmltable(XMLNAMESPACES('http://teiid.org' as \"teiid\"), "+   
                "  '/teiid:getplantWSResponse/return/PLANT' PASSING f.result COLUMNS "+   
                "  COMMON string     PATH 'COMMON', "+  
                "  BOTANICAL  string     PATH 'BOTANICAL') AS PLANT";*/
        /*select_par_sql = "SELECT PLANT.* from (call plantWS.invokeHttp(action=>'GET')) f,"+    
                "XMLTABLE(XMLNAMESPACES('http://teiid.org' as \"teiid\"), "+    
                    "  '/teiid:getplantWSResponse/return/PLANT' PASSING XMLPARSE(DOCUMENT f.result WELLFORMED) COLUMNS "+   
                    "  COMMON string    PATH 'COMMON', "+  
                    "  BOTANICAL  string    PATH 'BOTANICAL') AS PLANT";*/
        
        /*select_par_sql = "SELECT PLANT.* from (call plantWS.invokeHttp(action=>'GET')) f,"+    
                "xmltable(XMLNAMESPACES('http://teiid.org' as \"teiid\"), "+    
                    "  '/teiid:getplantWSResponse/return/PLANT' PASSING f.result COLUMNS "+   
                    "  COMMON string    PATH 'COMMON', "+  
                    "  BOTANICAL  string    PATH 'BOTANICAL') AS PLANT";*/
        
        /*select_par_sql = "SELECT PLANT.* from (call plantWS.invokeHttp(action=>'GET')) f,"+   
        "XMLTABLE(XMLNAMESPACES('http://teiid.org' as \"teiid\"), "+   
            "  '/teiid:getplantWSResponse/CATALOG/PLANT' PASSING XMLPARSE(DOCUMENT f.result WELLFORMED) COLUMNS "+  
            "  COMMON string    PATH 'COMMON', "+ 
            "  BOTANICAL  string    PATH 'BOTANICAL') AS PLANT";*/
        
        /*select_par_sql = "SELECT PLANT.* from (call plantWs.invokeHttp(action=>'GET')) f, "+   
        "XMLTABLE('/CATALOG/PLANT' PASSING XMLPARSE(DOCUMENT f.result WELLFORMED) COLUMNS "+   
         "  COMMON string PATH 'COMMON',  "+
         "  BOTANICAL string  PATH 'BOTANICAL') AS PLANT ";*/  
        
        /*select_par_sql = "SELECT "+ 
        "A.COMMON AS COMMON, A.BOTANICAL AS BOTANICAL"+
    " FROM"+
    " (EXEC departmentWS.invokeHttp('GET', null, 'https://www.w3schools.com/xml/plant_catalog.xml', 'TRUE')) AS f, XMLTABLE('/CATALOG' PASSING XMLPARSE(DOCUMENT f.result) COLUMNS COMMON string PATH 'PLANT/COMMON/text()', BOTANICAL string PATH 'PLANT/BOTANICAL/text()') AS A";*/
        
        /*select_par_sql = "INSERT INTO x_table VALUES"+ 
        "(1, "+
        "XMLPARSE(DOCUMENT '"+
            "<roster>"+
            "  <student age=\"18\">AB</student>"+
             " <student age=\"23\">BC</student>"+
              "<student>NOAGE</student>"+
            "</roster>'"+
          " PRESERVE WHITESPACE)"+
        ")";*/
         
//         select_par_sql = "INSERT INTO Customer(\"_id\",FirstName,LastName) VALUES (?, ?,?)";
//         select_par_sql = "UPDATE Customer SET customer_id = 123456789";
//         select_par_sql = "INSERT INTO conn.Customer1(\"_id\",FirstName,LastName) VALUES (?,?,?)";
        
        //select_par_sql = "select 'Y' from (SELECT agent_tin_id from  MySQL_Server.bitool.AGENT_DIM where agent_tin_id>100 EXISTS (SELECT agent_tin_id from MySQL_Server.bitool.AGENT_DIM where agent_tin_id=100)) as a;";
//        select_par_sql = "SELECT tablename from sys.columns where schemaname='SQLServerN' limit 1";
        /*select_par_sql = "SELECT agent_tin_id from MySQL_Server.bitool.AGENT_DIM where agent_tin_id>100 except "
        + "SELECT agent_tin_id from MySQL_Server.bitool.AGENT_DIM where agent_tin_id<100";*/
//        select_par_sql = "SELECT  agent_tin_id from (SELECT agent_tin_id from MySQL_Server.bitool.AGENT_DIM where agent_tin_id>100 except SELECT agent_tin_id from MySQL_Server.bitool.AGENT_DIM where agent_tin_id<100) a";
//        executeBatch(connection, select_par_sql);
         
//         select_par_sql = "INSERT INTO Address(CustomerId ,Street,City,State,Zipcode) VALUES ( ?, ?,?,?, ?)";
//         executeBatch123(connection, select_par_sql);
        
//        select_par_sql = "SELECT Customer._id, count(city) from Address, Customer where Customer._id = Address._id group by Customer._id";
//        select_par_sql = "SELECT _id from Address, Customer where Customer.FirstName = Address.FirstName";
//        select_par_sql = "SELECT \"Customer1._id\" from Customer1";
//        select_par_sql = "UPDATE Customer SET FirstName = 'kul' WHERE FirstName = 'abcd'";
        
//        select_par_sql = "insert into mysqlbitool.bitool.employee";
//        select_par_sql = "SELECT * from sqlcre.dq.dbo.employee";
        select_par_sql = "SELECT * from sql_teju.DQ.dbo.AGENT_DIM";
//        select_par_sql = "insert into bitool.EmployeeContact select * from dq.dbo.EmployeeContact"; 
        
        /*select_par_sql = "SELECT isnumeric(SampleSourceFile.StratDesc) from (call department.getTextFiles('SampleSourceFile.txt')) f, "+   
"TEXTTABLE(f.file COLUMNS InvAssetType string, InvType string,InvAssetTypeUserDef string, StratDesc string   DELIMITER '|' HEADER) SampleSourceFile where InvAssetType IN('CSH', 'CASHEQ', 'EQYNLT') AND InvType = 'NONTRADEEXP' AND InvAssetTypeUserDef != 'nonOTC' group by StratDesc;";*/
        
        
        select_par_sql = "SELECT COUNT(StratDesc) FROM SampleSourceFile "+ 
        "WHERE InvAssetType NOT IN('CSH', 'CASHEQ', 'EQYNLT') "+
        " OR "+
         "InvType  !='NONTRADEEXP' "+
         "OR "+
         "InvAssetTypeUserDef = 'nonOTC' "; 
        
//        select_par_sql = "SELECT tablename from sys.columns where schemaname='FF_Source' limit 1";
//        select_par_sql = "SELECT marketdataUDF.celsiusToFahrenheit(10) as celsis  from marketdata.world.city limit 1";
//        select_par_sql = "SELECT MysqlXADS2.celsiusToFahrenheit(10) as celsis  from MysqlXADS2.world.city limit 1";
//        select_par_sql = "SELECT MysqlXADS2UDF.celsiusToFahrenheit(10) as celsis, MysqlXADS2UDF.abcd('ds') from MysqlXADS2.world.city limit 1";
        
        /*connection.setAutoCommit(false);
        
        select_par_sql = "INSERT into MysqlXADS3.sakila.sakila_trasaction_table values(4,'John')";
        //call 1
        execute(connection, select_par_sql);
        
        select_par_sql = "INSERT into MysqlXADS2.world.world_trans_table values(5,'Books')";
        //call 2
        execute(connection, select_par_sql);
        
//        connection.rollback();
        connection.commit();*/
        
        /*select_par_sql = "INSERT into MysqlXADS3.sakila.sakila_trasaction_table(cardnumber,card_name)"
        		+ " SELECT id,book_name from MysqlXADS2.world.world_trans_table";
        //call 2
        execute(connection, select_par_sql);*/
    	
        select_par_sql = "SELECT name,TEST_myConnection150UDF.abcd('ds') FROM TEST_myConnection150.world.city";
        
        select_par_sql = "SELECT * FROM TEST_hive_movies.actors_main";
        
//        select_par_sql = "SELECT * FROM MysqlXADS3.movies.actors";
        
        select_par_sql = "SELECT MysqlXADS3.movies.actors.id,MysqlXADS3.movies.actors.name FROM MysqlXADS3.movies.actors, TEST_hive_movies.actors_main WHERE TEST_hive_movies.actors_main.id = MysqlXADS3.movies.actors.id";
//        AND TEST_hive_movies.actors_main.id NOT IN (MysqlXADS3.movies.actors.id)
        
        select_par_sql = "SELECT a.id FROM MysqlXADS3.movies.actors a FULL OUTER JOIN TEST_hive_movies.actors_main b "
        		+ "ON a.id=b.id WHERE a.id IS NULL OR b.id IS NULL";
        
        select_par_sql = "SELECT s.* from MysqlXADS3.movies.actors s full outer join TEST_hive_movies.actors_main t on s.id = t.id where s.name != t.name";
        select_par_sql = "SELECT s.* from MysqlXADS3.movies.actors s full outer join TEST_hive_movies.actors_main t on s.id = t.id where s.name != t.name and s.id <= 6 limit 100";
        
//        select_par_sql = "UPDATE TEST_hive_movies.actors_main SET rec_insert_time = '2020-02-21 11:05:40.0' WHERE id = 1";
        
        execute(connection, select_par_sql); 
        
    	connection.close(); 

        /*
         * select_par_sql =
         * "SELECT AES_DECRYPT(AES_ENCRYPT('1','plesuer'),'plesuer') FROM mysqlconn.aakash_test.test"
         * ;
         * 
         * execute(connection, select_par_sql);
         */
         
         
         
    }
    
    private static void executeBatch(Connection connection, String select_par_sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(select_par_sql);
        ps.setString(1, "91");
        ps.setString(2, "BHUSHAN");
        ps.setString(3, "kul");
        ps.addBatch();
        ps.setString(1, "92");
        ps.setString(2, "kul");
        ps.setString(3, "kul");
        ps.addBatch();
        
        /*ps.setString(1, "fasd");
        ps.setString(2, "fdsa");
//        ps.setString(3, "kul");
        ps.addBatch();
        ps.setString(1, "qwer");
        ps.setString(2, "ouuj");
//        ps.setString(3, "kul");
        ps.addBatch();*/
        
        int []updateCnt = ps.executeBatch();
        connection.commit();
        
        System.out.println(updateCnt[0]+" "+updateCnt[1]);
        
    }
    
    
    private static void executeBatch123(Connection connection, String select_par_sql) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(select_par_sql);
        ps.setString(1, "1");
        ps.setString(2, "BHUSHAN");
        ps.setString(3, "kul");
        ps.setString(4, "abcd");
        ps.setString(5, "fdsafs");
//        ps.setString(6, "BHUSHAN");
        ps.addBatch();
        ps.setString(1, "2");
        ps.setString(2, "kul");
        ps.setString(3, "kul");
        ps.setString(4, "7567");
        ps.setString(5, "12345");
//        ps.setString(6, "kul");
        ps.addBatch();
        
        int []updateCnt = ps.executeBatch();
        connection.commit();
        
        System.out.println(updateCnt[0]+" "+updateCnt[1]);
        
    }

    private static void executePackSql() throws Exception, SQLException {
        String selectSql = "call TestEvenNt.native('SELECT * from marketdata where PRICE=21.80 ')";

        String deleteSql = "call TestEvenNt.native('DELETE from marketdata where PRICE=21.80 ')";

        String del = "DELETE from TestEven.marketdata where PRICE=54.55";

        String packsql = "call TestEvenNt.native('PACK TABLE marketdata')";

        String selectfile = "SELECT * FROM marketdatacopy";

        Connection connection = getDriverConnection();

        DatabaseMetaData meta = connection.getMetaData();
        ResultSet schemas = meta.getSchemas();

        while (schemas.next()) {
            String tableSchema = schemas.getString(1); // "TABLE_SCHEM"
            String tableCatalog = schemas.getString(2); // "TABLE_CATALOG"
            System.out.println("tableSchema  == " + tableSchema);
            System.out.println("tableCatalog   == " + tableCatalog);
        }

        // execute(connection, selectfile);

        // execute(connection, selectSql);

        // executeDdl(connection, deleteSql);

        // executeDdl(connection, packsql);

        // execute(connection, selectSql);

    }

    private static void checkForaignKey() throws Exception, SQLException {

        String select_sql = "SELECT * FROM teiidVersion.aakash_test1.SUPPLIER2";
        String select_par_sql = "SELECT * FROM teiidVersion.aakash_test1.SUPPLIER";
        String insert_sql = " INSERT into teiidVersion.aakash_test1.SUPPLIER2 (SUPPLIER_ID_as_FK,SUPPLIER_NAME, CONTACT_NAME) values (5,'test2','test1')";
        // String delete_sql = "delete from teiidVersion.TDM.SUPPLIER2 where
        // SUPPLIER_ID_as_FK=5";

        String enable_key = "call native('SET FOREIGN_KEY_CHECKS=1')";
        String disable_key = "call native('SET FOREIGN_KEY_CHECKS=0')";

        Connection connection = getDriverConnection();

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Parent data");
        execute(connection, select_par_sql);

        System.out.println("child data");
        execute(connection, select_sql);

        System.out.println("Disable Foraign key");
        executeDdl(connection, disable_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Insert data which voilate constraint");
        execute(connection, insert_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable Foraign key");
        executeDdl(connection, enable_key);
        //
        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Delete data");
        // execute(connection, delete_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable primary key");
        executeDdl(connection, enable_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        connection.commit();

        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    private static void checkUniqueKey() throws Exception, SQLException {

        String select_sql = "SELECT * FROM teiidVersion.aakash_test1.SUPPLIER2";
        String select_par_sql = "SELECT * FROM teiidVersion.aakash_test1.SUPPLIER";
        String insert_sql = " INSERT into teiidVersion.aakash_test1.SUPPLIER2 (SUPPLIER_ID_as_FK,SUPPLIER_NAME, CONTACT_NAME) values (1,'test2','test1')";
        // String delete_sql = "delete from teiidVersion.aakash_test1.SUPPLIER2
        // where SUPPLIER_ID_as_FK=5";

        String enable_key = "call native('SET UNIQUE_CHECKS = 1')";
        String disable_key = "call native('SET UNIQUE_CHECKS = 0')";

        Connection connection = getDriverConnection();

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Parent data");
        execute(connection, select_par_sql);

        System.out.println("child data");
        execute(connection, select_sql);

        System.out.println("Disable Unique key");
        executeDdl(connection, disable_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Insert data which voilate constraint");
        execute(connection, insert_sql);
        execute(connection, insert_sql);
        execute(connection, insert_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable Unique key");
        executeDdl(connection, enable_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        // System.out.println("Delete data");
        // execute(connection, delete_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable primary key");
        executeDdl(connection, enable_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        connection.commit();

        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    private static void checkPrimaryKey() throws Exception, SQLException {

        String select_sql = "SELECT * FROM teiidVersion.aakash_test1.supplier";
        String insert_sql = " INSERT into teiidVersion.TDM.SUPPLIER1 (SUPPLIER_ID,SUPPLIER_NAME, CONTACT_NAME) values (2,'test2','test1')";
        String delete_sql = "delete from teiidVersion.TDM.SUPPLIER1 where SUPPLIER_ID=2";

        String enable_primary_key = "call native('ALTER TABLE SUPPLIER1 ENABLE CONSTRAINT SUPPLIER1_PK')";
        String disable_primary_key = "call native('ALTER TABLE SUPPLIER1 DISABLE CONSTRAINT SUPPLIER1_PK')";

        Connection connection = getDriverConnection();

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Disable primary key");
        executeDdl(connection, disable_primary_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Insert data which voilate constraint");
        execute(connection, insert_sql);
        execute(connection, insert_sql);
        execute(connection, insert_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable primary key");
        executeDdl(connection, enable_primary_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("Delete data");
        execute(connection, delete_sql);

        System.out.println("All data");
        execute(connection, select_sql);

        System.out.println("Enable primary key");
        executeDdl(connection, enable_primary_key);

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        connection.commit();

        if (connection != null) {
            connection.close();
        }
        connection = null;
    }

    static Connection getDriverConnection() throws Exception {
        System.out.println("Used values{host = " + host + "}{Port = " + port + " }{vdb name = " + vdb + " }");
        String url = "jdbc:teiid:" + vdb + "@mm://" + host + ":" + port + ";showplan=on"; // note
                                                                                            // showplan
                                                                                            // setting
        // String url =
        // "jdbc:teiid:"+vdb+"@mm://"+host+":31000,10.110.250.147:31000;showplan=on";

        // String url =
        // "jdbc:text:ftp://bitwise21:bit@wise21@ftp.bitwiseglobal.com/TDM/testFiles/HeaderTrueDelimited/marketdatacopy.txt?_CSV_Separator=,;_CSV_Header=True;fileExtension=txt";
        Class.forName("org.teiid.jdbc.TeiidDriver");

        return DriverManager.getConnection(url, "user", pwd);
    }

    /*
     * static Connection getDataSourceConnection(String host, String port,
     * String vdb) throws Exception { TeiidDataSource ds = new
     * TeiidDataSource(); ds.setDatabaseName(vdb); ds.setUser("user");
     * ds.setPassword(pwd); ds.setServerName(host);
     * ds.setPortNumber(Integer.valueOf(port));
     * 
     * ds.setShowPlan("on"); //turn show plan on
     * 
     * return ds.getConnection(); }
     */

    public static void executeDdl(Connection connection, String sql) throws Exception {
        if (sql == null)
            return;
        try {
            System.out.println("Used values{SQL = " + sql + "}");
            Statement statement = connection.createStatement();

            boolean b = statement.execute(sql);

            System.out.println("Query Excute => " + b);
            statement.close();

        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.print(e.getMessage());
        } finally {

        }
        System.out.println(" ");
    }

    public static void executeSqlUpdate(String sql) throws Exception {
        if (sql == null)
            return;
        try {
            System.out.println("Used values{SQL = " + sql + "}");
            Connection connection = getDriverConnection();
            Statement statement = connection.createStatement();

            ResultSet res = statement.executeQuery(sql);

            // System.out.println("Query Excute => " + b);
            while (res.next()) {
                System.out.println(res.getString(1));
            }
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
        } finally {

        }
        System.out.println(" ");
    }

    private static void executeSqlInBatch() throws Exception {
        Connection connection = getDriverConnection();
        // PreparedStatement pstatement = connection.prepareStatement("INSERT
        // INTO mysqlKulaakashs.aakash_test.TEST VALUES(?)");
        Statement pstatement = connection.createStatement();
        try {

            /*
             * pstatement.setString(1, "abc"); pstatement.addBatch();
             * pstatement.setString(1, "pqr"); pstatement.addBatch();
             */
            String value = "'abc'";
            pstatement.addBatch("INSERT INTO mysqlKulaakashs.aakash_test.TEST VALUES(" + value + ")");
            value = "'aaa'";
            pstatement.addBatch("INSERT INTO mysqlKulaakashs.aakash_test.TEST VALUES(" + value + ")");
            /*
             * pstatement.addBatch(
             * "INSERT INTO oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\"(oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PARTY_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PROCESSOR_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_TYPE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_AFT_HRS_HELP_DESK_SUPPORT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_AMEX_ESA_AGENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CHARGEBACKS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_COLLECTIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CONVERSIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CREDIT_UNDERWRITING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CUSTOM_ENCRYPTION\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CUSTOMER_SERVICE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_DOWNLOADS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_FULL_HELP_DESK_SUPPORT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_HARDWARE_DEPLOYMENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_MAS_NEW_ADD_EDIT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_PLATE_ORDERS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_REPLACEMENTS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_RISK_MANAGEMENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_STAND_IN_PROCESSING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_SUPPLIES\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_TRAINING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_VOICE_AUTHORIZATIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_WELCOME_KITS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NBR_STANDARD_CHAR\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_FORMAT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"RETENTION_PERIOD\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"STATUS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"USER_INTERFACE_SYSTEM_NAME\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATION_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATED_BY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATE_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATED_BY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATE_LOGIN\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"OBJECT_VERSION_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATED_BY_MODULE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PURCHASE_VELOCITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREDIT_VELOCITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUT_TIME_EFFECTIVE_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"VOICE_CENTER_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_PRIORITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"END_OF_DAY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"SPLIT_FUNDING_FLAG\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"MAS_PROCESSING_INDICATOR\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_SEQUENCING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_TYPE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_SERVICE_PHONE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LINE_OF_BUSINESS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_NAME\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PARENT_CUSTOMER_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_PHONE_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"TIME_ZONE1\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"SHIP_TO_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"STATUS_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_SPECIFIC_LOGIC_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"USE_CUST_ADD_FOR_CONTACT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"UPDATED_BY_MODULE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"FILE_REFERENCE_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE1\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE2\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE3\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"OPEN_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_OPEN_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFUND_ALLOWED_PER_IND_BATCH\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"TOT_AMT_PER_IND_BATCH\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"BIN_BLOCKING_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"AVS_MAPPING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ISO_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"AX_RETAINED\")VALUES('2', 5555, 387517266125, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '1941-06-27', 100, '1929-01-03', 1, 2, null, null, null, null, '1901-04-11', 100, null, null, null, null, null, null, null, null, 'Doris', null, null, null, null, null, null, 'a', null, null, null, null, null, null, '2017-03-01 20:19:00', null, null, null, null, null, 'F')"
             * + ""); pstatement.addBatch(
             * "INSERT INTO oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\"(oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PARTY_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PROCESSOR_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_TYPE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_AFT_HRS_HELP_DESK_SUPPORT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_AMEX_ESA_AGENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CHARGEBACKS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_COLLECTIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CONVERSIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CREDIT_UNDERWRITING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CUSTOM_ENCRYPTION\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_CUSTOMER_SERVICE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_DOWNLOADS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_FULL_HELP_DESK_SUPPORT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_HARDWARE_DEPLOYMENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_MAS_NEW_ADD_EDIT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_PLATE_ORDERS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_REPLACEMENTS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_RISK_MANAGEMENT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_STAND_IN_PROCESSING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_SUPPLIES\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_TRAINING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_VOICE_AUTHORIZATIONS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"IS_WELCOME_KITS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NBR_STANDARD_CHAR\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_FORMAT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"RETENTION_PERIOD\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"STATUS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"USER_INTERFACE_SYSTEM_NAME\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATION_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATED_BY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATE_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATED_BY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_UPDATE_LOGIN\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"OBJECT_VERSION_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREATED_BY_MODULE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PURCHASE_VELOCITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CREDIT_VELOCITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUT_TIME_EFFECTIVE_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"VOICE_CENTER_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_PRIORITY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"END_OF_DAY\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"SPLIT_FUNDING_FLAG\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"MAS_PROCESSING_INDICATOR\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_SEQUENCING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFERENCE_NUMBER_TYPE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_SERVICE_PHONE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LINE_OF_BUSINESS\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_NAME\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"PARENT_CUSTOMER_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_PHONE_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"TIME_ZONE1\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"SHIP_TO_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"STATUS_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"CUSTOMER_SPECIFIC_LOGIC_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"USE_CUST_ADD_FOR_CONTACT\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"UPDATED_BY_MODULE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"FILE_REFERENCE_NUMBER\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE1\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE2\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ATTRIBUTE3\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"OPEN_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"LAST_OPEN_DATE\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"REFUND_ALLOWED_PER_IND_BATCH\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"TOT_AMT_PER_IND_BATCH\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"BIN_BLOCKING_IND\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"AVS_MAPPING\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"ISO_ID\",oracleKul.HARSHS.\"TEST_CUSTOMER_DETAILS\".\"AX_RETAINED\")VALUES('3', 5555, 387517266125, 1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, '1', null, '1941-06-27', 100, '1929-01-03', 1, 2, null, null, null, null, '1901-04-11', 100, null, null, null, null, null, null, null, null, 'Doris', null, null, null, null, null, null, 'a', null, null, null, null, null, null, '2017-03-01 20:19:00', null, null, null, null, null, 'F')"
             * + "");
             */

            int b[] = pstatement.executeBatch();
            System.out.println("b " + b);
            System.out.println("Query Excute => " + pstatement);

        } catch (SQLException e) {
            e.printStackTrace();
            // System.out.print(e.getMessage());
        } finally {
            connection.commit();
            pstatement.close();
            connection.close();
        }
        System.out.println(" done ");
    }

    public static void execute(Connection connection, String sql) throws Exception {
        if (sql == null)
            return;
        try {
            System.out.println("Used values{SQL = " + sql + "}");
            System.out.println("Setting autocommit false");
            Statement statement = connection.createStatement();

            ResultSet results = null;
            if (sql.contains("INSERT") || sql.contains("UPDATE")) {
                int rwo_insert = statement.executeUpdate(sql);
                System.out.println(rwo_insert);

            } else {
                results = statement.executeQuery(sql);
                ResultSetMetaData metadata = results.getMetaData();
                int columns = metadata.getColumnCount();
                
                for (int i = 1; i <= columns; i++ ) {
                      String name = metadata.getColumnName(i);
                      System.out.print(name+",");
                    }
                int rowCnt = 0;
                System.out.println("\nResults");
                for (int row = 1; results.next(); row++) {
                    System.out.print(row + ": ");
                    for (int i = 0; i < columns; i++) {
                        if (i > 0) {
                            System.out.print(",");
                        }
                        System.out.print(results.getString(i + 1));
                        
                    }
                    System.out.println();
                    rowCnt++;
                    
                }
                System.out.println("rowCnt "+rowCnt);

                results.close();
                statement.close();
//                connection.close();
            }

            /*
             * PreparedStatement ps1 = connection.prepareStatement(sql);
             * ps1.setString(1, "xxx"); ps1.setString(2, "abc"); ps1.addBatch();
             * ps1.executeBatch(); System.out.println("excuted");
             */

        } catch (SQLException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } finally {
        	
        }
        System.out.println(" ");
    }

    public static void executeHXTTBatch() throws Exception {
        try {
            Connection con = getDriverConnection();

            PreparedStatement ps = con.prepareStatement("INSERT INTO mkt VALUES(?,?)");
            ps.setObject(1, "123");
            ps.setObject(2, "AAA");
            ps.addBatch();
            ps.setObject(1, "222");
            ps.setObject(2, "BBB");
            ps.addBatch();
            ps.setObject(1, "333");
            ps.setObject(2, "CCC");
            ps.addBatch();
            ps.setObject(1, "444");
            ps.setObject(2, "DDD");
            ps.addBatch();
            ps.setObject(1, "555");
            ps.setObject(2, "EEE");
            ps.addBatch();

            int[] updateCount = ps.executeBatch();
            ps.close();

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select count(*) from mkt");
            if (rs.next()) {
                System.out.println(rs.getInt(1));
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void executeForClob(Connection connection, String sql) throws Exception {
        if (sql == null)
            return;
        try {
            System.out.println("Used values{SQL = " + sql + "}");
            Statement statement = connection.createStatement();

            ResultSet results = null;
            if (!sql.contains("SELECT")) {
                int rwo_insert = statement.executeUpdate(sql);
                System.out.println(rwo_insert);

            } else {
                results = statement.executeQuery(sql);
                ResultSetMetaData metadata = results.getMetaData();
                int columns = metadata.getColumnCount();
                System.out.println("Results");
                for (int row = 1; results.next(); row++) {
                    System.out.print(row + ": ");
                    for (int i = 0; i < columns; i++) {
                        if (i > 0) {
                            System.out.print(",");
                        }
                        // System.out.print(results.getString(i + 1));
                        // System.out.print(results.getClob(i + 1).toString());
                        // Clob clob = results.getClob(i + 1);
                        System.out.print(results.getBlob(i + 1).toString());
                    }
                    System.out.println();
                }

                results.close();
                statement.close();
            }
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } finally {

        }
        System.out.println(" ");
    }

    private static void checkForClob() throws Exception, SQLException {

        // String select_sql = "SELECT * FROM clobconn.aakash_test.clob_tbl";
        String insert_sql = " INSERT into teiidVersion.TDM.SUPPLIER1 (SUPPLIER_ID,SUPPLIER_NAME, CONTACT_NAME) values (2,'test2','test1')";
        String delete_sql = "delete from clobconn.aakash_test.clob_tbl where clobColumn = '%$^&W*FSDFDFDFSAFDDSDFDFF'";

        String enable_primary_key = "call native('ALTER TABLE SUPPLIER1 ENABLE CONSTRAINT SUPPLIER1_PK')";
        String disable_primary_key = "call native('ALTER TABLE SUPPLIER1 DISABLE CONSTRAINT SUPPLIER1_PK')";

        Connection connection = getDriverConnection();

        // System.out.println("List of all constraints");
        // execute(connection, get_all_constraints);
        //
        // System.out.println("List of all indexes");
        // execute(connection, get_all_indexes);

        System.out.println("All data");
        executeForClob(connection, delete_sql);
    }

    private static void executeNativeSql() throws Exception, SQLException {

        /*
         * E.G. stmt.execute(
         * "call sqlServerDStrans.native('SET IDENTITY_INSERT Persons ON')");
         * String insertTableQuery =
         * " INSERT INTO MySqlDStrans.aakash_test.customer(cid, cname, caddress, cmobile)VALUES('1','MARCUS','pune','123124')"
         * ; // String insertTableQuery ="insert into "+tableName+
         * " (LastName,FirstName,Address,City)values('Solutions','Bitwise','SB','Pune')"
         * ; stmt.executeUpdate(insertTableQuery); stmt.execute(
         * "call sqlServerDStrans.native('SET IDENTITY_INSERT Persons OFF')");
         * System.out.println(tableName+ " record insert Successfully");
         */
        String tableName = "TEST_CSV";

        // String setIdentityOn = "call "+ modelName +".native('SET
        // IDENTITY_INSERT Persons ON')";
        // String insertTableQuery ="insert into "+tableName+"
        // (LastName,FirstName,Address,City)values('Solutions','Bitwise','SB','Pune')";
        String insertTableQuery = "insert into " + modelName + ".HARSHS.TEST_CSV VALUES(6,7.7)";

        // String setIdentityOff = "call "+ modelName +".native('SET
        // IDENTITY_INSERT Persons Off')";

        Connection connection = getDriverConnection();
        Statement statement = connection.createStatement();

        // statement.execute(setIdentityOn);
        // statement.executeUpdate(insertTableQuery);
        // statement.execute("call oracleTestNative.native(insert into "+
        // modelName +".HARSHS.TEST_CSV VALUES(6,7.7))");
        // statement.execute("call oracleTestNative.native('insert into
        // oracleTestNative.HARSHS.TEST_CSV(COLUMN1,COLUMN2) VALUES(6,7.7);')");
        statement.execute("call oracleTestNative.native('delete from oracleTestNative.HARSHS.TEST_CSV;')");
        // statement.execute("call oracleTestNative.native('ALTER TABLE TEST_CSV
        // DROP CONSTRAINT TEST_CSV_PK')");
        statement.execute("call oracleTestNative.native('COMMIT')");
        // statement.execute(setIdentityOff);

        statement.close();
        connection.close();

        System.out.println("query executed");

    }

}
