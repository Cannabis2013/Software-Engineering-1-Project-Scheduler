package abstractions;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractManager {
	
	private List<Model> models = new ArrayList<Model>();
    
    protected void addModel(Model item)
    {
        models.add(item);
        item.setParentManager(this);
        requestUpdate();
    }

    protected void removeModel(String identity) throws Exception
    {
        for (int i = 0; i < models.size(); i++)
        {
            Model model = models.get(i);
            if (model.modelId().equals(identity))
            {
                models.remove(i);
                return;
            }
        }
        
        throw new Exception("Object not found.");
    }
    protected void removeModelAt(int index) throws Exception
    {
    	if(index >= models.size() || index < 0)
			throw new Exception("Index not valid");
    	models.remove(index);
    }
    
    protected Model model(Model item)
    {
    	for(Model model : models)
    	{
    		if(model == item)
    			return model;
    	}
    	
    	return null;
    }
    
    protected Model model(String id)
    {
    	for(Model model : models)
    	{
    		if(model.modelId().equals(id))
    			return model;
    	}
    	
    	throw new NullPointerException("No model with the given identity exists within the current context.");
    }
    
    protected Model modelAt(int index)
    {
    	if(index > models.size())
    		throw new NullPointerException("The model at the given index doesn't exist.");
    	return models.get(index);
    }
    
    protected List<Model> models()
    {
    	return models;
    }
    
    protected void setModels(List<Model> models)
    {
    	this.models = models;
    }

    protected List<String> allProjectNames()
    {
    	List<String> result = new ArrayList<>();
    	for(Model model : models)
    		result.add(model.modelId());
    	
    	return result;
    }

	public abstract void requestUpdate();
	
}
