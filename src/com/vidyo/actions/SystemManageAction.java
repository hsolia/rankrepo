package com.vidyo.actions;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.springframework.context.ConfigurableApplicationContext;

import com.vidyo.beans.ConfigBean;
import com.vidyo.daos.ConfigDAO;

@ManagedBean(name="systemManageAction")
@ViewScoped
public class SystemManageAction extends BaseAction
{
  private boolean settingsUpdated = false;
  private List<ConfigBean> configList;

  public void initSystemEmail()
  {
    if (!FacesContext.getCurrentInstance().isPostback())
    {
      ConfigDAO configDao = (ConfigDAO)getBean("configDao");
      this.configList = configDao.getSystenSettings();
    }
  }

  public void editSystemSettings()
  {
    this.settingsUpdated = false;
  }

  public void updateSettings()
  {
    ConfigDAO configDao = (ConfigDAO)getBean("configDao");

    for (ConfigBean sysConfig : this.configList) {
      configDao.update(sysConfig);
    }
    
    ((ConfigurableApplicationContext)getCtx()).refresh();
    
    this.settingsUpdated = true;
  }
  public boolean isSettingsUpdated() {
    return this.settingsUpdated;
  }
  public void setSettingsUpdated(boolean settingsUpdated) {
    this.settingsUpdated = settingsUpdated;
  }

  public List<ConfigBean> getConfigList() {
    return this.configList;
  }

  public void setConfigList(List<ConfigBean> configList) {
    this.configList = configList;
  }
}