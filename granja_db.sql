--
-- PostgreSQL database dump
--

\restrict mRxnR1La0Yyn5j66WrjpUaB6aL3ewQUEpDFrn6kwKKidfNSodjS0Kk7JpXmEU5w

-- Dumped from database version 14.22
-- Dumped by pg_dump version 14.22

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: alimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.alimento (
    id integer NOT NULL,
    tipo character varying(50),
    cantidad_disponible numeric(10,2),
    fecha_caducidad date
);


ALTER TABLE public.alimento OWNER TO postgres;

--
-- Name: alimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.alimento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.alimento_id_seq OWNER TO postgres;

--
-- Name: alimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.alimento_id_seq OWNED BY public.alimento.id;


--
-- Name: ave; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ave (
    id integer NOT NULL,
    raza character varying(50),
    edad integer,
    peso numeric(5,2),
    estado_salud character varying(50),
    galpon_id integer
);


ALTER TABLE public.ave OWNER TO postgres;

--
-- Name: ave_alimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ave_alimento (
    id integer NOT NULL,
    ave_id integer,
    alimento_id integer,
    cantidad_consumida numeric(10,2),
    fecha date
);


ALTER TABLE public.ave_alimento OWNER TO postgres;

--
-- Name: ave_alimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ave_alimento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ave_alimento_id_seq OWNER TO postgres;

--
-- Name: ave_alimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ave_alimento_id_seq OWNED BY public.ave_alimento.id;


--
-- Name: ave_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ave_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ave_id_seq OWNER TO postgres;

--
-- Name: ave_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ave_id_seq OWNED BY public.ave.id;


--
-- Name: consumo_alimento; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.consumo_alimento (
    id integer NOT NULL,
    ave_id integer,
    alimento_id integer,
    cantidad integer NOT NULL,
    fecha timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT consumo_alimento_cantidad_check CHECK ((cantidad > 0))
);


ALTER TABLE public.consumo_alimento OWNER TO postgres;

--
-- Name: consumo_alimento_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.consumo_alimento_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.consumo_alimento_id_seq OWNER TO postgres;

--
-- Name: consumo_alimento_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.consumo_alimento_id_seq OWNED BY public.consumo_alimento.id;


--
-- Name: empleado; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleado (
    id integer NOT NULL,
    nombre character varying(100),
    cargo character varying(50),
    turno character varying(50),
    granja_id integer
);


ALTER TABLE public.empleado OWNER TO postgres;

--
-- Name: empleado_galpon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.empleado_galpon (
    id integer NOT NULL,
    empleado_id integer,
    galpon_id integer,
    actividad character varying(100),
    fecha date
);


ALTER TABLE public.empleado_galpon OWNER TO postgres;

--
-- Name: empleado_galpon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empleado_galpon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empleado_galpon_id_seq OWNER TO postgres;

--
-- Name: empleado_galpon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_galpon_id_seq OWNED BY public.empleado_galpon.id;


--
-- Name: empleado_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.empleado_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.empleado_id_seq OWNER TO postgres;

--
-- Name: empleado_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.empleado_id_seq OWNED BY public.empleado.id;


--
-- Name: galpon; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.galpon (
    id integer NOT NULL,
    capacidad integer NOT NULL,
    granja_id integer
);


ALTER TABLE public.galpon OWNER TO postgres;

--
-- Name: galpon_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.galpon_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.galpon_id_seq OWNER TO postgres;

--
-- Name: galpon_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.galpon_id_seq OWNED BY public.galpon.id;


--
-- Name: granja; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.granja (
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    ubicacion character varying(150),
    fecha_creacion timestamp without time zone DEFAULT CURRENT_TIMESTAMP
);


ALTER TABLE public.granja OWNER TO postgres;

--
-- Name: granja_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.granja_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.granja_id_seq OWNER TO postgres;

--
-- Name: granja_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.granja_id_seq OWNED BY public.granja.id;


--
-- Name: produccion; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.produccion (
    id integer NOT NULL,
    fecha date,
    cantidad_huevos integer,
    galpon_id integer
);


ALTER TABLE public.produccion OWNER TO postgres;

--
-- Name: produccion_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.produccion_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.produccion_id_seq OWNER TO postgres;

--
-- Name: produccion_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.produccion_id_seq OWNED BY public.produccion.id;


--
-- Name: alimento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alimento ALTER COLUMN id SET DEFAULT nextval('public.alimento_id_seq'::regclass);


--
-- Name: ave id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave ALTER COLUMN id SET DEFAULT nextval('public.ave_id_seq'::regclass);


--
-- Name: ave_alimento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave_alimento ALTER COLUMN id SET DEFAULT nextval('public.ave_alimento_id_seq'::regclass);


--
-- Name: consumo_alimento id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumo_alimento ALTER COLUMN id SET DEFAULT nextval('public.consumo_alimento_id_seq'::regclass);


--
-- Name: empleado id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado ALTER COLUMN id SET DEFAULT nextval('public.empleado_id_seq'::regclass);


--
-- Name: empleado_galpon id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado_galpon ALTER COLUMN id SET DEFAULT nextval('public.empleado_galpon_id_seq'::regclass);


--
-- Name: galpon id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galpon ALTER COLUMN id SET DEFAULT nextval('public.galpon_id_seq'::regclass);


--
-- Name: granja id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.granja ALTER COLUMN id SET DEFAULT nextval('public.granja_id_seq'::regclass);


--
-- Name: produccion id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion ALTER COLUMN id SET DEFAULT nextval('public.produccion_id_seq'::regclass);


--
-- Data for Name: alimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.alimento (id, tipo, cantidad_disponible, fecha_caducidad) FROM stdin;
1	Concentrado	1000.00	2026-12-31
2	Concentrado	1000.00	2026-12-31
3	Concentrado	1000.00	2026-12-31
\.


--
-- Data for Name: ave; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ave (id, raza, edad, peso, estado_salud, galpon_id) FROM stdin;
\.


--
-- Data for Name: ave_alimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.ave_alimento (id, ave_id, alimento_id, cantidad_consumida, fecha) FROM stdin;
\.


--
-- Data for Name: consumo_alimento; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.consumo_alimento (id, ave_id, alimento_id, cantidad, fecha) FROM stdin;
\.


--
-- Data for Name: empleado; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empleado (id, nombre, cargo, turno, granja_id) FROM stdin;
\.


--
-- Data for Name: empleado_galpon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.empleado_galpon (id, empleado_id, galpon_id, actividad, fecha) FROM stdin;
\.


--
-- Data for Name: galpon; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.galpon (id, capacidad, granja_id) FROM stdin;
\.


--
-- Data for Name: granja; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.granja (id, nombre, ubicacion, fecha_creacion) FROM stdin;
1	Granja El Progreso	Colombia	2026-04-22 20:36:03.540431
2	Granja El Progreso	Colombia	2026-04-22 20:36:03.540431
3	Granja El Progreso	Colombia	2026-04-22 20:37:37.898484
4	sdfsdf	sdfsdf	2026-04-22 20:38:52.771345
\.


--
-- Data for Name: produccion; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.produccion (id, fecha, cantidad_huevos, galpon_id) FROM stdin;
\.


--
-- Name: alimento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.alimento_id_seq', 3, true);


--
-- Name: ave_alimento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ave_alimento_id_seq', 1, false);


--
-- Name: ave_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ave_id_seq', 1, false);


--
-- Name: consumo_alimento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.consumo_alimento_id_seq', 1, false);


--
-- Name: empleado_galpon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_galpon_id_seq', 1, false);


--
-- Name: empleado_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.empleado_id_seq', 1, false);


--
-- Name: galpon_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.galpon_id_seq', 1, false);


--
-- Name: granja_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.granja_id_seq', 4, true);


--
-- Name: produccion_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.produccion_id_seq', 1, false);


--
-- Name: alimento alimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.alimento
    ADD CONSTRAINT alimento_pkey PRIMARY KEY (id);


--
-- Name: ave_alimento ave_alimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave_alimento
    ADD CONSTRAINT ave_alimento_pkey PRIMARY KEY (id);


--
-- Name: ave ave_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave
    ADD CONSTRAINT ave_pkey PRIMARY KEY (id);


--
-- Name: consumo_alimento consumo_alimento_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumo_alimento
    ADD CONSTRAINT consumo_alimento_pkey PRIMARY KEY (id);


--
-- Name: empleado_galpon empleado_galpon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado_galpon
    ADD CONSTRAINT empleado_galpon_pkey PRIMARY KEY (id);


--
-- Name: empleado empleado_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_pkey PRIMARY KEY (id);


--
-- Name: galpon galpon_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galpon
    ADD CONSTRAINT galpon_pkey PRIMARY KEY (id);


--
-- Name: granja granja_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.granja
    ADD CONSTRAINT granja_pkey PRIMARY KEY (id);


--
-- Name: produccion produccion_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion
    ADD CONSTRAINT produccion_pkey PRIMARY KEY (id);


--
-- Name: ave_alimento ave_alimento_alimento_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave_alimento
    ADD CONSTRAINT ave_alimento_alimento_id_fkey FOREIGN KEY (alimento_id) REFERENCES public.alimento(id);


--
-- Name: ave_alimento ave_alimento_ave_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave_alimento
    ADD CONSTRAINT ave_alimento_ave_id_fkey FOREIGN KEY (ave_id) REFERENCES public.ave(id);


--
-- Name: ave ave_galpon_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ave
    ADD CONSTRAINT ave_galpon_id_fkey FOREIGN KEY (galpon_id) REFERENCES public.galpon(id);


--
-- Name: consumo_alimento consumo_alimento_alimento_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumo_alimento
    ADD CONSTRAINT consumo_alimento_alimento_id_fkey FOREIGN KEY (alimento_id) REFERENCES public.alimento(id) ON DELETE CASCADE;


--
-- Name: consumo_alimento consumo_alimento_ave_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.consumo_alimento
    ADD CONSTRAINT consumo_alimento_ave_id_fkey FOREIGN KEY (ave_id) REFERENCES public.ave(id) ON DELETE CASCADE;


--
-- Name: empleado_galpon empleado_galpon_empleado_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado_galpon
    ADD CONSTRAINT empleado_galpon_empleado_id_fkey FOREIGN KEY (empleado_id) REFERENCES public.empleado(id);


--
-- Name: empleado_galpon empleado_galpon_galpon_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado_galpon
    ADD CONSTRAINT empleado_galpon_galpon_id_fkey FOREIGN KEY (galpon_id) REFERENCES public.galpon(id);


--
-- Name: empleado empleado_granja_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.empleado
    ADD CONSTRAINT empleado_granja_id_fkey FOREIGN KEY (granja_id) REFERENCES public.granja(id);


--
-- Name: galpon galpon_granja_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.galpon
    ADD CONSTRAINT galpon_granja_id_fkey FOREIGN KEY (granja_id) REFERENCES public.granja(id);


--
-- Name: produccion produccion_galpon_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.produccion
    ADD CONSTRAINT produccion_galpon_id_fkey FOREIGN KEY (galpon_id) REFERENCES public.galpon(id);


--
-- PostgreSQL database dump complete
--

\unrestrict mRxnR1La0Yyn5j66WrjpUaB6aL3ewQUEpDFrn6kwKKidfNSodjS0Kk7JpXmEU5w

