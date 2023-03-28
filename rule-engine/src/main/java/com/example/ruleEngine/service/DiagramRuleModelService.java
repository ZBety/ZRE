package com.example.ruleEngine.service;

import com.example.ruleEngine.domain.layout.DiagramRuleModel;
import com.example.ruleEngine.repositories.DiagramRuleModelRepository;
import com.example.ruleEngine.util.StreamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiagramRuleModelService {

        @Autowired
        private DiagramRuleModelRepository drmRepo;

        public void create(DiagramRuleModel diagram) {
            diagram.setId(UUID.randomUUID().toString());
            diagram.setNo(diagram.getId());
            drmRepo.save(diagram);
        }

        public List<DiagramRuleModel> list() {
            return StreamUtil.iterableToList(drmRepo.findAll());
        }

        public DiagramRuleModel getDiagramById(String diagramId) {
            return drmRepo.findById(diagramId).get();
        }

        public void deleteDiagram(String diagramId) {
            drmRepo.deleteById(diagramId);
        }

        public void updateDiagram(DiagramRuleModel diagramRuleModel) {
            drmRepo.save(diagramRuleModel);
        }
}
