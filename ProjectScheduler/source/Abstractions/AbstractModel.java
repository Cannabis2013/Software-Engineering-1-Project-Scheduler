package Abstractions;

import java.util.ArrayList;
import java.util.List;

import formComponents.ItemModel;

public abstract class AbstractModel {

	private AbstractModel parent = null;
	private AbstractManager parentManager = null;
	private List<AbstractModel> subModels = new ArrayList<AbstractModel>();

	private String ModelId;
	
	public String modelIdentity()
	{
		return ModelId;
	}
	
	public void setModelidentity(String id)
	{
		ModelId = id;
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

	public void AddSubModel(AbstractModel subModel) {
		subModels.add(subModel);
		subModel.setParent(this);
		StateChanged();
	}

	public void RemoveSubModel(AbstractModel SubModel) {
		subModels.remove(SubModel);
		SubModel.parent = null;
		StateChanged();
	}

	public void RemoveSubModel(String identity) {
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
	
	public AbstractModel SubModel(String SubModelIdentity)
	{
		for(AbstractModel model : subModels)
		{
			if(model.modelIdentity().equals(SubModelIdentity))
				return model;
		}
		return null;
	}
	
	public AbstractModel SubModelAt(int index)
	{
		return subModels.get(index);
	}
	
	public List<AbstractModel> SubModels()
	{
		return subModels;
	}
	
	public void setSubModels(List<AbstractModel> models)
	{
		subModels = models;
	}

	//public ListViewItem[] allSubItemModels() => subModels.Select(item => item.ItemModel()).ToArray();

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
