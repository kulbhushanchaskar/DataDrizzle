package com.datadrizzle.connection.translators;

import java.util.Arrays;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.teiid.webui.share.beans.TeiidConnection;

import com.datadrizzle.entities.DataDrizzleConnection;
import com.datadrizzle.share.ApplicationConstants;

@Service
public class ConnectionTranslator {
	DozerBeanMapper mapper = new DozerBeanMapper(Arrays.asList(new String[]{ApplicationConstants.dozerBeanMappingXML}));
	
	public TeiidConnection dataDrizzleConn2TeiidConn(DataDrizzleConnection connection) {
		return mapper.map(connection, TeiidConnection.class);
	}

}
