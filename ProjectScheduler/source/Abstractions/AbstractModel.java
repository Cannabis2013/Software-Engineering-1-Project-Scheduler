package Abstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import formComponents.ItemModel;

public abstract class AbstractModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 440923988337809655L;
	private AbstractModel parent = null;
	private AbstractManager parentManager = null;
	private List<AbstractModel> subModels = new ArrayList<AbstractModel>();

	private String modelTitle, textContent;
	
	public String modelIdentity()
	{
		return modelTitle;
	}
	
	public void setModelidentity(String id)
	{
		modelTitle = id;
	}
	
	public String description()
	{
		return textContent;
	}
	
	public void setDescription(String text)
	{
		textContent = text;
	}
	
	public AbstractModel Parent()
	{
		return parent;
    }
	
	public void setParent(AbstractModel parent)
	{
		this.parent = parent;
	}

	public AbstractManager parentManager()
	{
		return parentManager;
    }
	
	public void setParentManager(AbstractManager parentManager)
	{
		this.parentManager = parentManager;
	}

	public String parentModelIdentity() {
		return parent.modelIdentity();
	}

	//public abstract ListViewItem ItemModel();

	public void addSubModel(AbstractModel subModel) {
		subModels.add(subModel);
		subModel.setParent(this);
		StateChanged();
	}

	public void removeSubModel(AbstractModel SubModel) {
		subModels.remove(SubModel);
		SubModel.parent = null;
		StateChanged();
	}

	public void removeSubModel(String identity) {
		for (int i = 0; i < subModels.size(); i++) {
			AbstractModel model = subModels.get(i);
			if (model.modelIdentity().equals(identity)) {
				subModels.remove(i);
				StateChanged();
				return;
			}
		}
	}

	public void RemoveSubModelAt(int index) {
		subModels.remove(index);
		StateChanged();
	}
	
	public AbstractModel subModel(String SubModelIdentity)
	{
		for(AbstractModel model : subModels)
		{
			if(model.modelIdentity().equals(SubModelIdentity))
				return model;
		}
		return null;
	}
	
	public AbstractModel subModelAt(int index)
	{
		return subModels.get(index);
	}
	
	public List<AbstractModel> subModels()
	{
		return subModels;
	}
	
	public void setSubModels(List<AbstractModel> models)
	{
		subModels = models;
	}
	
	public <T> List<T> AllSubModels()
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
	
	public List<ItemModel> allSubItemModels()
	{
		List<ItemModel> models = new ArrayList<>();
		for(int i = 0;i <subModels().size();i++)
			models.add(subModels().get(i).itemModel());
		
		return models;
	}

	public void NotifyObservers()
    {
    }

	public void StateChanged()
    {
        AbstractModel ParentProject = WarnParentObject(this);
        ParentProject.parentManager.requestUpdate();
    }

	private AbstractModel WarnParentObject(AbstractModel model) {
		return model.parent != null ? WarnParentObject(model.parent) : model;
	}
	
	public abstract ItemModel itemModel();
	

}
