package dto;

import dto.ItestProjectVersionEntity;

import java.util.List;

/**
 * Created by yw on 2017/4/19.
 */
public class ItestProjectVersionDto extends ItestProjectVersionEntity{
    private List<ItestModuleDto> moduleDtos;

    public List<ItestModuleDto> getModuleDtos() {
        return moduleDtos;
    }

    public void setModuleDtos(List<ItestModuleDto> moduleDtos) {
        this.moduleDtos = moduleDtos;
    }

    @Override
    public String getProjectData() {
        return null;
    }

    @Override
    public void setProjectData(String projectData) {

    }
}
