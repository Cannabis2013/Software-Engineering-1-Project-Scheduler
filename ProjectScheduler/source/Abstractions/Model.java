package Abstractions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import formComponents.ItemModel;

public abstract class Model implements Serializable {

	private static final long serialVersionUID = 440923988337809655L;
	private Model parent = null;
	private AbstractManager parentManager = null;
	private List<Model> subModels = new ArrayList<Model>();
	private String id, text, serialId;
	
	public void setSerialId(String serial)
	{
		serialId = serial;
	}
	
	public String serialId()
	{
		return serialId;
	}
	
	public String modelId()
	{
		return id;
	}
	
	public void setModelidentity(String id)
	{
		this.id = id;
	}
	
	public String description()
	{
		return text;
	}
	
	public void setDescription(String text)
	{
		this.text = text;
	}
	
	public Model Parent()
	{
		return parent;
    }
	
	public void setParent(Model parent)
	{
		this.parent = parent;
	}

	protected AbstractManager parentManager()
	{
		return parentManager;
    }
	
	protected void setParentManager(AbstractManager parentManager)
	{
		this.parentManager = parentManager;
	}

	public String parentModelId() {
		return parent.modelId();
	}

	protected void addSubModel(Model subModel) {
		subModels.add(subModel);
		subModel.setParent(this);
		StateChanged();
	}

	protected void removeSubModel(Model SubModel) {
		subModels.remove(SubModel);
		SubModel.parent = null;
		StateChanged();
	}

	protected void removeSubModel(String identity) {
		for (int i = 0; i < subModels.size(); i++) {
			Model model = subModels.get(i);
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
	
	protected Model subModel(String SubModelIdentity)
	{
		for(Model model : subModels)
		{
			if(model.modelId().equals(SubModelIdentity))
				return model;
		}
		return null;
	}
	
	protected Model subModelAt(int index)
	{
		return subModels.get(index);
	}
	
	protected List<Model> subModels()
	{
		return subModels;
	}
	
	protected void setSubModels(List<Model> models)
	{
		subModels = models;
	}
	
	protected <T> List<T> AllSubModels()
    {
        List<T> result = new ArrayList<T>();
        for (Model model : subModels)
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
	
	protected List<ItemModel> allSubItemModels()
	{
		List<ItemModel> models = new ArrayList<>();
		for(int i = 0;i <subModels().size();i++)
			models.add(subModels().get(i).itemModel());
		
		return models;
	}

	private void StateChanged()
    {
        Model ParentProject = root(this);
        ParentProject.parentManager.requestUpdate();
    }

	protected Model root(Model model) {
		return model.parent != null ? root(model.parent) : model;
	}
	
	public abstract ItemModel itemModel();
	protected abstract String generateSerialId();
}
