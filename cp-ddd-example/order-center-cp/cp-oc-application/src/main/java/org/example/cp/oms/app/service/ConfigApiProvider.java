package org.example.cp.oms.app.service;

import lombok.extern.slf4j.Slf4j;
import org.cdf.ddd.runtime.registry.Container;
import org.example.cp.oms.client.api.ConfigApi;
import org.example.cp.oms.client.dto.ConfigRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;

@Service
@Slf4j
public class ConfigApiProvider implements ConfigApi {

    @Override
    public void signalPartner(@NotNull ConfigRequest request) {
        // 先卸载
        Container.getInstance().unloadPartnerPlugin(request.getPartnerCode());

        // 再加载
        try {
            Container.getInstance().loadPartnerPlugin(request.getJarURL().toString(), "org.example.bp.oms");
        } catch (Throwable ex) {
            log.error("load:{}", request, ex);
        }
    }

}