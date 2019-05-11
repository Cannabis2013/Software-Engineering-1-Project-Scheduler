package abstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Manager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3209292946300694287L;
	private List<AbstractModel> models = new ArrayList<AbstractModel>();
	protected List<ICustomObserver> observers = new ArrayList<ICustomObserver>();
    
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
            if (model.modelId().equals(identity))
            {
                models.remove(i);
                requestUpdate();
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
    		if(model.modelId().equals(id))
    			return model;
    	}
    	
    	throw new NullPointerException("No model with the given identity exists within the current context.");
    }
    
    protected AbstractModel modelAt(int index)
    {
    	if(index > models.size())
    		throw new NullPointerException("The model at the given index doesn't exist.");
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

    protected List<String> allModelIdentities()
    {
    	List<String> result = new ArrayList<>();
    	for(AbstractModel model : models)
    		result.add(model.modelId());
    	
    	return result;
    }

    public void requestUpdate() {
		for (ICustomObserver observer : observers)
			observer.updateView();
	}
}
