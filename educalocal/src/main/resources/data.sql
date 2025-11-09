INSERT INTO usuarios (id, nome, email, senha, tipo, bairro, bio)
VALUES
  (1, 'Administrador EducaLocal', 'admin@educalocal.com', 'admin123', 'ADMIN', 'Centro', 'Administrador do sistema'),
  (2, 'Maria Mentor', 'maria.mentor@educalocal.com', 'mentor123', 'MENTOR', 'Jardim das Flores', 'Mentora em planilhas e currículo'),
  (3, 'João Aprendiz', 'joao.aprendiz@educalocal.com', 'aprendiz123', 'APRENDIZ', 'Vila Esperança', 'Em transição de carreira');

INSERT INTO habilidades (id, nome, descricao)
VALUES
  (1, 'Planilhas Básicas', 'Uso básico de planilhas e fórmulas simples'),
  (2, 'Currículo e Entrevista', 'Criação de currículo e preparação para entrevistas'),
  (3, 'Atendimento ao Cliente', 'Noções básicas de atendimento e comunicação'),
  (4, 'Lógica de Programação', 'Fundamentos de lógica e pensamento computacional');

INSERT INTO usuarios_habilidades (usuario_id, habilidade_id)
VALUES
  (2, 1),
  (2, 2),
  (2, 3),
  (3, 4);

INSERT INTO trilhas (id, titulo, descricao, vagas, ativa, mentor_id)
VALUES
  (1, 'Introdução a Planilhas para Empregabilidade', 'Trilha rápida focada em planilhas para rotinas administrativas', 10, TRUE, 2);

INSERT INTO trilhas_habilidades (trilha_id, habilidade_id)
VALUES
  (1, 1);

INSERT INTO modulos (id, titulo, descricao, ordem, trilha_id)
VALUES
  (1, 'Primeiros passos em planilhas', 'Interface, células e formatação básica', 1, 1),
  (2, 'Fórmulas essenciais', 'Soma, média, mínimo e máximo', 2, 1);

INSERT INTO inscricoes (id, usuario_id, trilha_id, status, data_inscricao, concluida)
VALUES
  (1, 3, 1, 'ATIVA', NOW(), FALSE);

INSERT INTO sessoes (id, trilha_id, mentor_id, aprendiz_id, data_hora, modo, local, status, vagas)
VALUES
  (1, 1, 2, 3, NOW() + INTERVAL '1 day', 'ONLINE', 'Link de videoconferência', 'AGENDADA', 5);

INSERT INTO avaliacoes (id, trilha_id, avaliador_id, avaliado_id, nota, comentario, data_hora)
VALUES
  (1, 1, 3, 2, 5, 'Mentora muito clara e atenciosa', NOW());
