package com.simbirsoft.maketalents.resume_builder.model.core.data.impl.concurrently;

import com.simbirsoft.maketalents.resume_builder.model.core.data.ManagerDataSource;
import com.simbirsoft.maketalents.resume_builder.model.core.data.impl.properties_file_loader.ManagerDataSourceImpl;

/**
 * Class for getting resume from property file in separate thread
 */
public class PropertyReader extends Provider {

    public PropertyReader(){
        super.setManagerDataSource(new ManagerDataSourceImpl());
        setDescription("provider gets resume from property file");
    }

    @Override
    public void setManagerDataSource(ManagerDataSource managerDataSource) {
        //nothing
    }
}
