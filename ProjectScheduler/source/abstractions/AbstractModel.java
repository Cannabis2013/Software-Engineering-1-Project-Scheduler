package abstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import models.ItemModel;
import sun.security.provider.certpath.BuildStep;

public abstract class AbstractModel implements Serializable {

	private static final long serialVersionUID = 440923988337809655L;
	private AbstractModel parent = null;
	private Manager parentManager = null;
	private List<AbstractModel> subModels = new ArrayList<AbstractModel>();
	private String id, text;
	
	
	protected String modelId()
	{
		return id;
	}
	
	protected void setModelidentity(String id)
	{
		this.id = id;
		StateChanged();
	}
	
	public String description()
	{
		return text;
	}
	
	public void setDescription(String text)
	{
		this.text = text;
		StateChanged();
	}
	
	public AbstractModel Parent()
	{
		return parent;
    }
	
	public void setParent(AbstractModel parent)
	{
		this.parent = parent;
		StateChanged();
	}

	protected Manager parentManager()
	{
		return parentManager;
    }
	
	protected void setParentManager(Manager parentManager)
	{
		this.parentManager = parentManager;
		StateChanged();
	}

	public String parentModelId() {
		return parent.modelId();
	}

	protected void addSubModel(AbstractModel subModel) {
		subModels.add(subModel);
		subModel.setParent(this);
		
		int index = 0;
		StringBuilder buildedString = new StringBuilder(modelId());
		if(parent != null)
		{
			AbstractModel parentModel;
			parentModel = parent;
			
			List<AbstractModel> parentChildrens = parentModel.subModels();
			for(AbstractModel model : parentChildrens)
			{
				if(model.modelId().equals(modelId()))
					index++;
			}
			String indexString = String.format("(%d)", index++);
			buildedString.append(indexString);	
		}
		else if(parentManager != null)
		{
			Manager parentModel = parentManager();
			
			List<AbstractModel> parentChildrens = parentModel.models();
			for(AbstractModel model : parentChildrens)
			{
				if(model.modelId().equals(id))
					index++;
			}
			if(index > 1)
			{
				String indexString = String.format("(%d)", index++);
				buildedString.append(indexString);				
			}
		}
		
		setModelidentity(buildedString.toString());
		
		StateChanged();
	}

	protected void removeSubModel(AbstractModel SubModel) {
		subModels.remove(SubModel);
		SubModel.parent = null;
		StateChanged();
	}

	protected void removeSubModel(String identity) {
		for (int i = 0; i < subModels.size(); i++) {
			AbstractModel model = subModels.get(i);
			if (model.modelId().equals(identity)) {
				subModels.remove(i);
				StateChanged();
				return;
			}
		}
	}

	protected void RemoveSubModelAt(int index) {
		subModels.remove(index);
		StateChanged();
	}
	
	protected AbstractModel subModel(String SubModelIdentity)
	{
		for(AbstractModel model : subModels)
		{
			if(model.modelId().equals(SubModelIdentity))
				return model;
		}
		throw new NullPointerException("No model with the given identity exists in the current context.");
	}
	
	protected AbstractModel subModelAt(int index)
	{
		return subModels.get(index);
	}
	
	protected <T> List<T> subModels()
	{
		List<T> result = new ArrayList<T>();
        for (AbstractModel model : subModels)
        {
        	if(model instanceof Object)
        	{
        		@SuppressWarnings("unchecked")
				T item = (T) model;
        		result.add(item);        		
        	}
        }

        return result;
	}
	
	protected void removeAllSubModels()
	{
		subModels.clear();
		StateChanged();
	}
	
	protected void setSubModels(List<AbstractModel> models)
	{
		subModels = models;
		StateChanged();
	}
	
	protected List<ItemModel> allSubItemModels()
	{
		List<ItemModel> itemModels = new ArrayList<>();
		List<AbstractModel> models = subModels();
		for(int i = 0;i <models.size();i++)
			itemModels.add(models.get(i).itemModel());
		
		return itemModels;
	}

	public void StateChanged()
    {
        AbstractModel rootModel = root(this);
        if(rootModel.parentManager != null)
        	rootModel.parentManager.requestUpdate();
    }

	protected AbstractModel root(AbstractModel model) {
		return model.parent != null ? root(model.parent) : model;
	}
	
	public abstract ItemModel itemModel();
}
