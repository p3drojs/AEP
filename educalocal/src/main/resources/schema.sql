CREATE TABLE usuarios (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(150) NOT NULL,
  email VARCHAR(150) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  bairro VARCHAR(120),
  bio TEXT
);

CREATE TABLE habilidades (
  id BIGSERIAL PRIMARY KEY,
  nome VARCHAR(100) NOT NULL UNIQUE,
  descricao TEXT
);

CREATE TABLE usuarios_habilidades (
  usuario_id BIGINT NOT NULL,
  habilidade_id BIGINT NOT NULL,
  PRIMARY KEY (usuario_id, habilidade_id),
  CONSTRAINT fk_usuarios_habilidades_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  CONSTRAINT fk_usuarios_habilidades_habilidade
    FOREIGN KEY (habilidade_id) REFERENCES habilidades (id)
);

CREATE TABLE trilhas (
  id BIGSERIAL PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  descricao TEXT,
  vagas INTEGER NOT NULL,
  ativa BOOLEAN NOT NULL DEFAULT TRUE,
  mentor_id BIGINT NOT NULL,
  CONSTRAINT fk_trilhas_mentor
    FOREIGN KEY (mentor_id) REFERENCES usuarios (id)
);

CREATE TABLE trilhas_habilidades (
  trilha_id BIGINT NOT NULL,
  habilidade_id BIGINT NOT NULL,
  PRIMARY KEY (trilha_id, habilidade_id),
  CONSTRAINT fk_trilhas_habilidades_trilha
    FOREIGN KEY (trilha_id) REFERENCES trilhas (id),
  CONSTRAINT fk_trilhas_habilidades_habilidade
    FOREIGN KEY (habilidade_id) REFERENCES habilidades (id)
);

CREATE TABLE modulos (
  id BIGSERIAL PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  descricao TEXT,
  ordem INTEGER NOT NULL,
  trilha_id BIGINT NOT NULL,
  CONSTRAINT fk_modulos_trilha
    FOREIGN KEY (trilha_id) REFERENCES trilhas (id)
);

CREATE TABLE inscricoes (
  id BIGSERIAL PRIMARY KEY,
  usuario_id BIGINT NOT NULL,
  trilha_id BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL,
  data_inscricao TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  concluida BOOLEAN NOT NULL DEFAULT FALSE,
  CONSTRAINT fk_inscricoes_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuarios (id),
  CONSTRAINT fk_inscricoes_trilha
    FOREIGN KEY (trilha_id) REFERENCES trilhas (id),
  CONSTRAINT uq_inscricao_usuario_trilha
    UNIQUE (usuario_id, trilha_id)
);

CREATE TABLE sessoes (
  id BIGSERIAL PRIMARY KEY,
  trilha_id BIGINT NOT NULL,
  mentor_id BIGINT NOT NULL,
  aprendiz_id BIGINT NOT NULL,
  data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  modo VARCHAR(20) NOT NULL,
  local VARCHAR(200),
  status VARCHAR(20) NOT NULL,
  vagas INTEGER,
  CONSTRAINT fk_sessoes_trilha
    FOREIGN KEY (trilha_id) REFERENCES trilhas (id),
  CONSTRAINT fk_sessoes_mentor
    FOREIGN KEY (mentor_id) REFERENCES usuarios (id),
  CONSTRAINT fk_sessoes_aprendiz
    FOREIGN KEY (aprendiz_id) REFERENCES usuarios (id)
);

CREATE TABLE avaliacoes (
  id BIGSERIAL PRIMARY KEY,
  trilha_id BIGINT NOT NULL,
  avaliador_id BIGINT NOT NULL,
  avaliado_id BIGINT NOT NULL,
  nota INTEGER NOT NULL,
  comentario TEXT,
  data_hora TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  CONSTRAINT fk_avaliacoes_trilha
    FOREIGN KEY (trilha_id) REFERENCES trilhas (id),
  CONSTRAINT fk_avaliacoes_avaliador
    FOREIGN KEY (avaliador_id) REFERENCES usuarios (id),
  CONSTRAINT fk_avaliacoes_avaliado
    FOREIGN KEY (avaliado_id) REFERENCES usuarios (id)
);
