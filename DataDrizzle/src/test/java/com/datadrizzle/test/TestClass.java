package com.datadrizzle.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.teiid.webui.share.beans.DataSourceDetailsBean;
import org.teiid.webui.share.beans.DataSourcePropertyBean;
import org.teiid.webui.share.beans.DataSourceWithVdbDetailsBean;
import org.teiid.webui.share.beans.ViewModelRequestBean;
import org.teiid.webui.share.exceptions.DataVirtUiException;
import org.teiid.webui.share.services.ITeiidService;

import com.datadrizzle.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class})
public class TestClass {

	@Autowired
	private ITeiidService teiidService;

	@Test
	public void testConnection() {
		teiidService.testConnection("MysqlXADS");
	}
	
	@Test
	public void createMysqlDSWithVDB() throws DataVirtUiException {
        
    	DataSourceWithVdbDetailsBean dbVDBBean = new DataSourceWithVdbDetailsBean();
    	dbVDBBean.setCreatedBy("DataDrizzle");
    	dbVDBBean.setName("MySqlDS2");
    	dbVDBBean.setSourceVdbName("SVC_MySQLConnection");
    	dbVDBBean.setTranslator("mysql5");
    	dbVDBBean.setJndiName("java:/MySQLDS2");
    	
    	/*DataSourceDetailsBean dsDetailsBean = new DataSourceDetailsBean();
    	dsDetailsBean.setName("MySqlDS2");
    	dsDetailsBean.setJndiName("java:/MySQLDS2");
    	dsDetailsBean.setCreatedBy("DataDrizzle");
    	dsDetailsBean.setVersion("1");*/
    	List<DataSourcePropertyBean> dsPropBeanList = new ArrayList<>(); 

    	dsPropBeanList.add(new DataSourcePropertyBean().setName("jndi-name").setValue("java:/MysqlXADS2"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("pool-name").setValue("MysqlXADS2"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("enabled").setValue("true"));
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("use-ccm").setValue("true"));
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("jta").setValue("true"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("connection-url").setValue("jdbc:mysql://localhost:3306/test"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("driver-class").setValue("com.mysql.jdbc.Driver"));
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("Driver").setValue("mysql"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("user-name").setValue("root"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("password").setValue("root"));
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("background-validation").setValue("true"));
    	
    	/*dsPropBeanList.add(new DataSourcePropertyBean().setName("valid-connection-checker class-name"). setValue("org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("exception-sorter class-name").setValue("org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"));*/
    	
    	
    	dbVDBBean.setProperties(dsPropBeanList);
    	dbVDBBean.setType("mysql");
    	/*dsPropBeanList.add(new DataSourcePropertyBean().setName("ServerName").setValue("localhost"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("DatabaseName").setValue("test"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("xa-datasource-class").setValue("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"));*/
    	
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("driver").setValue("mysql"));
    	
    	teiidService.createDataSourceWithVdb(dbVDBBean, 5000);
    	
	}
	
	@Test
	public void createMysqlXADSWithVDB() throws DataVirtUiException {
    	DataSourceWithVdbDetailsBean dbVDBBean = new DataSourceWithVdbDetailsBean();
    	dbVDBBean.setCreatedBy("DataDrizzle");
    	dbVDBBean.setName("MysqlXADS2");
    	dbVDBBean.setSourceVdbName("SVC_MySQLConnection");
    	dbVDBBean.setTranslator("mysql5");
    	dbVDBBean.setJndiName("java:/MySQLDS2");
    	
    	String ddl = "CREATE VIRTUAL FUNCTION celsiusToFahrenheit(celsius DOUBLE) RETURNS DOUBLE OPTIONS (JAVA_CLASS   'org.something.TempConv',  JAVA_METHOD 'celsiusToFahrenheit'); "
    			+ "CREATE VIRTUAL FUNCTION abcd(data STRING) RETURNS STRING OPTIONS (JAVA_CLASS   'org.something.TempConv',  JAVA_METHOD 'abcd');";
    	
    	dbVDBBean.setDDL(ddl);
    	
    	List<DataSourcePropertyBean> dsPropBeanList = new ArrayList<>(); 

    	dsPropBeanList.add(new DataSourcePropertyBean().setName("jndi-name").setValue("java:/MysqlXADS2"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("pool-name").setValue("MysqlXADS2"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("enabled").setValue("true"));
    	
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("connection-url").setValue("jdbc:mysql://localhost:3306/test"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("driver-class").setValue("com.mysql.jdbc.Driver"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("user-name").setValue("root"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("password").setValue("root"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("max-pool-size").setValue("20"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("min-pool-size").setValue("10"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("prefill").setValue("true"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("use-java-context").setValue("true"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("xa-datasource-class").setValue("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("DatabaseName").setValue("world"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("ServerName").setValue("localhost"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("PortNumber").setValue("3306"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("prefill").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("use-strict-min").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("flush-strategy").setValue("FailingConnectionOnly"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("is-same-rm-override").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("no-tx-separate-pools").setValue("false"));
    	
    	dbVDBBean.setProperties(dsPropBeanList);
    	dbVDBBean.setType("mysql" + "-xa");
    	
    	teiidService.createDataSourceWithVdb(dbVDBBean, 5000);
	}
	
	@Test
	public void test_Mysql_Connection_XADS_With_VDB() throws DataVirtUiException {
    	List<String> dataSourcesListToDelete = new ArrayList<>();
    	dataSourcesListToDelete.add("MysqlXADS2");
    	teiidService.deleteDataSources(dataSourcesListToDelete);
    	teiidService.deleteDynamicVdbsWithPrefix("SVC_MySQLConnection");
	}
	
	@Test
	public void create_2nd_Mysql_XADS_WithVDB() throws DataVirtUiException {
    	DataSourceWithVdbDetailsBean dbVDBBean = new DataSourceWithVdbDetailsBean();
    	dbVDBBean.setCreatedBy("DataDrizzle");
    	dbVDBBean.setName("MysqlXADS3");
    	dbVDBBean.setSourceVdbName("SVC_MySQLConnection_sakila");
    	dbVDBBean.setTranslator("mysql5");
    	dbVDBBean.setJndiName("java:/MySQLDS3");
    	
    	String ddl = "CREATE VIRTUAL FUNCTION celsiusToFahrenheit(celsius DOUBLE) RETURNS DOUBLE OPTIONS (JAVA_CLASS   'org.something.TempConv',  JAVA_METHOD 'celsiusToFahrenheit'); "
    			+ "CREATE VIRTUAL FUNCTION abcd(data STRING) RETURNS STRING OPTIONS (JAVA_CLASS   'org.something.TempConv',  JAVA_METHOD 'abcd');";
    	
    	dbVDBBean.setDDL(ddl);
    	
    	List<DataSourcePropertyBean> dsPropBeanList = new ArrayList<>(); 

    	dsPropBeanList.add(new DataSourcePropertyBean().setName("jndi-name").setValue("java:/MysqlXADS3"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("pool-name").setValue("MysqlXADS3"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("enabled").setValue("true"));
    	
//    	dsPropBeanList.add(new DataSourcePropertyBean().setName("connection-url").setValue("jdbc:mysql://localhost:3306/test"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("driver-class").setValue("com.mysql.jdbc.Driver"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("user-name").setValue("root"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("password").setValue("root"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("max-pool-size").setValue("20"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("min-pool-size").setValue("10"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("prefill").setValue("true"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("use-java-context").setValue("true"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("xa-datasource-class").setValue("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("DatabaseName").setValue("sakila"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("ServerName").setValue("localhost"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("PortNumber").setValue("3306"));
    	
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("prefill").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("use-strict-min").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("flush-strategy").setValue("FailingConnectionOnly"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("is-same-rm-override").setValue("false"));
    	dsPropBeanList.add(new DataSourcePropertyBean().setName("no-tx-separate-pools").setValue("false"));
    	
    	dbVDBBean.setProperties(dsPropBeanList);
    	dbVDBBean.setType("mysql" + "-xa");
    	
    	teiidService.createDataSourceWithVdb(dbVDBBean, 5000);
	}
	
	@Test
	public void create_Combine_VDB() throws DataVirtUiException {
		String connection1 = "SVC_MySQLConnection_sakila";
		String connection2 = "SVC_MySQLConnection";
		
		List<String> connectionList = new ArrayList<>();
		connectionList.add(connection1);
		connectionList.add(connection2);
		
		List<Integer> connectionListVersions = new ArrayList<>();
		connectionListVersions.add(1);
		connectionListVersions.add(1);
		
//		final String vdbName, final int vdbVersion, final Map<String,String> vdbPropMap, final ViewModelRequestBean viewModelRequest
		String vdbName = "combine4";
		int vdbVersion = 1;
		Map<String,String> vdbPropMap = new HashMap<>();
		ViewModelRequestBean viewModelRequest = new ViewModelRequestBean();
		viewModelRequest.setName(vdbName); 
		viewModelRequest.setRequiredImportVdbNames(connectionList);
		viewModelRequest.setRequiredImportVdbVersions(connectionListVersions);
		
//		createCombineVDB(connectionList);
		teiidService.deployCombineVDBs(vdbName, vdbVersion, vdbPropMap, viewModelRequest);
		
		
	}
	

}
