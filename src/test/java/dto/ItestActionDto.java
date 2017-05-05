package dto;

import dto.ItestActionEntity;

import java.util.List;

/**
 * Created by yw on 2017/4/19.
 */
public class ItestActionDto extends ItestActionEntity{
    /**
     * 参数
     */
    private ItestParameterEntity parameterEntity;
    private List<ItestRequestParametersDto> requestParametersDtos;
    private List<ItestResponseParametersDto> responseParametersDtos;

    public ItestParameterEntity getParameterEntity() {
        return parameterEntity;
    }

    public void setParameterEntity(ItestParameterEntity parameterEntity) {
        this.parameterEntity = parameterEntity;
    }

    public List<ItestRequestParametersDto> getRequestParametersDtos() {
        return requestParametersDtos;
    }

    public void setRequestParametersDtos(List<ItestRequestParametersDto> requestParametersDtos) {
        this.requestParametersDtos = requestParametersDtos;
    }

    public List<ItestResponseParametersDto> getResponseParametersDtos() {
        return responseParametersDtos;
    }

    public void setResponseParametersDtos(List<ItestResponseParametersDto> responseParametersDtos) {
        this.responseParametersDtos = responseParametersDtos;
    }
}
