package Abstractions;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractManager {
	
	private List<AbstractModel> models = new ArrayList<AbstractModel>();
    
    protected void addModel(AbstractModel item)
    {
        models.add(item);
        item.setParentManager(this);
        requestUpdate();
    }

    protected void removeModel(String identity) throws Exception
    {
        for (int i = 0; i < models.size(); i++)
        {
            AbstractModel model = models.get(i);
            if (model.modelIdentity().equals(identity))
            {
                models.remove(i);
                return;
            }
        }
        
        throw new Exception("Object not found.");
    }
    protected void removeModelAt(int index)
    {
    	models.remove(index);
    }
    
    protected AbstractModel model(AbstractModel item)
    {
    	for(AbstractModel model : models)
    	{
    		if(model == item)
    			return model;
    	}
    	
    	return null;
    }
    
    protected AbstractModel model(String id)
    {
    	for(AbstractModel model : models)
    	{
    		if(model.modelIdentity().equals(id))
    			return model;
    	}
    	
    	return null;
    }
    
    protected AbstractModel modelBySerial(String serial) throws Exception
    {
    	for (AbstractModel model : models()) {
			if(model.serialId().equals(serial))
				return model;
			
			AbstractModel childModel = iterateModel(model,serial);
			if(childModel != null)
				return childModel;
		}
		
		throw new Exception("Model not found");
    }
    
    protected AbstractModel modelAt(int index)
    {
    	return models.get(index);
    }
    
    protected List<AbstractModel> models()
    {
    	return models;
    }
    
    protected void setModels(List<AbstractModel> models)
    {
    	this.models = models;
    }

    protected List<String> ListModelIdentities()
    {
    	List<String> result = new ArrayList<>();
    	for(AbstractModel model : models)
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
