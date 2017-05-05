package dto;

import dto.ItestPageEntity;

import java.util.List;

/**
 * Created by yw on 2017/4/19.
 */
public class ItestPageDto extends ItestPageEntity{

    private List<ItestActionDto> actionDtos;

    public List<ItestActionDto> getActionDtos() {
        return actionDtos;
    }

    public void setActionDtos(List<ItestActionDto> actionDtos) {
        this.actionDtos = actionDtos;
    }
}
