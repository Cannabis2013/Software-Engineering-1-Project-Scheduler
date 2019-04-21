package Abstractions;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractManager {
	
	private List<AbstractModel> modelList = new ArrayList<AbstractModel>();
    
    public void addModel(AbstractModel item)
    {
        modelList.add(item);
        item.setParentManager(this);
        requestUpdate();
    }

    public void removeModel(String identity) throws Exception
    {
        for (int i = 0; i < modelList.size(); i++)
        {
            AbstractModel model = modelList.get(i);
            if (model.modelIdentity().equals(identity))
            {
                modelList.remove(i);
                return;
            }
        }
        
        throw new Exception("Object not found.");
    }
    public void removeModelAt(int index)
    {
    	modelList.remove(index);
    }
    
    public AbstractModel model(AbstractModel item)
    {
    	for(AbstractModel model : modelList)
    	{
    		if(model == item)
    			return model;
    	}
    	
    	return null;
    }
    
    public AbstractModel model(String id)
    {
    	for(AbstractModel model : modelList)
    	{
    		if(model.modelIdentity().equals(id))
    			return model;
    	}
    	
    	return null;
    }
    
    public AbstractModel modelBySerial(String serial)
    {
    	for (AbstractModel model : models()) {
			if(model.serialId().equals(serial))
				return model;
			
			AbstractModel childModel = iterateModel(model,serial);
			if(childModel != null)
				return childModel;
		}
		
		return null;
    }
    
    public AbstractModel modelAt(int index)
    {
    	return modelList.get(index);
    }
    
    public List<AbstractModel> models()
    {
    	return modelList;
    }
    
    public void setModels(List<AbstractModel> models)
    {
    	modelList = models;
    }

    public List<String> ListModelIdentities()
    {
    	List<String> result = new ArrayList<>();
    	for(AbstractModel model : modelList)
    		result.add(model.modelIdentity());
    	
    	return result;
    }

	
	public abstract void requestUpdate();
	
	private AbstractModel iterateModel(AbstractModel model, String serialId)
	{
		if(model.serialId().equals(serialId))
			return model;
		
		for (AbstractModel aModel : model.subModels()) {
			AbstractModel aM = iterateModel(aModel, serialId);
			if(aM != null)
				return aM;
		}

		return null;
	}
	
}
