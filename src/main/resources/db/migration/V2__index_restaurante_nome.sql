CREATE INDEX IF NOT EXISTS idx_restaurante_nome_unaccent
    ON core_restaurante (unaccent(lower(nome)));