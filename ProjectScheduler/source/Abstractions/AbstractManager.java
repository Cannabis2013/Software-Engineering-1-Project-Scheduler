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

    public void removeModel(String identity)
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
    
    public AbstractModel modelAt(int index)
    {
    	return modelList.get(index);
    }
    
    public List<AbstractModel> Models()
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
	
}
