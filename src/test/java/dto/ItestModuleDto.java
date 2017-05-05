package dto;

import dto.ItestModuleEntity;

import java.util.List;

/**
 * Created by yw on 2017/4/19.
 */
public class ItestModuleDto extends ItestModuleEntity{
    private List<ItestPageDto> pageDtos;

    public List<ItestPageDto> getPageDtos() {
        return pageDtos;
    }

    public void setPageDtos(List<ItestPageDto> pageDtos) {
        this.pageDtos = pageDtos;
    }
}
