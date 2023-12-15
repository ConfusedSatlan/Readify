DO $$
    DECLARE
        table_name text;
    BEGIN
        FOR table_name IN (SELECT table_name FROM information_schema.tables WHERE table_schema = 'test' AND table_type = 'BASE TABLE')
            LOOP
                EXECUTE 'DROP TABLE IF EXISTS ' || table_name || ' CASCADE';
            END LOOP;
    END;
$$;