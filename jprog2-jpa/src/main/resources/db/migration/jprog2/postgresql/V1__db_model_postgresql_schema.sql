--
-- PostgreSQL database dump
--

-- Dumped from database version 10.4
-- Dumped by pg_dump version 10.4

-- Started on 2018-09-08 14:21:33

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 197 (class 1259 OID 16390)
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id_address bigint NOT NULL,
    city character varying(45) NOT NULL,
    house_number integer NOT NULL,
    street character varying(45) NOT NULL,
    zip_code character varying(45)
);


ALTER TABLE public.address OWNER TO postgres;

--
-- TOC entry 196 (class 1259 OID 16388)
-- Name: address_id_address_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.address_id_address_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.address_id_address_seq OWNER TO postgres;

--
-- TOC entry 2245 (class 0 OID 0)
-- Dependencies: 196
-- Name: address_id_address_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.address_id_address_seq OWNED BY public.address.id_address;


--
-- TOC entry 199 (class 1259 OID 16398)
-- Name: contact; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact (
    id_contact bigint NOT NULL,
    contact character varying(45) NOT NULL,
    id_contact_type bigint,
    id_user bigint
);


ALTER TABLE public.contact OWNER TO postgres;

--
-- TOC entry 198 (class 1259 OID 16396)
-- Name: contact_id_contact_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contact_id_contact_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_id_contact_seq OWNER TO postgres;

--
-- TOC entry 2246 (class 0 OID 0)
-- Dependencies: 198
-- Name: contact_id_contact_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contact_id_contact_seq OWNED BY public.contact.id_contact;


--
-- TOC entry 201 (class 1259 OID 16406)
-- Name: contact_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.contact_type (
    id_contact_type bigint NOT NULL,
    title character varying(45) NOT NULL
);


ALTER TABLE public.contact_type OWNER TO postgres;

--
-- TOC entry 200 (class 1259 OID 16404)
-- Name: contact_type_id_contact_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.contact_type_id_contact_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.contact_type_id_contact_type_seq OWNER TO postgres;

--
-- TOC entry 2247 (class 0 OID 0)
-- Dependencies: 200
-- Name: contact_type_id_contact_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.contact_type_id_contact_type_seq OWNED BY public.contact_type.id_contact_type;


--
-- TOC entry 203 (class 1259 OID 16414)
-- Name: meeting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.meeting (
    id_meeting bigint NOT NULL,
    meeting_datetime timestamp without time zone NOT NULL,
    note character varying(1000),
    place character varying(200) NOT NULL
);


ALTER TABLE public.meeting OWNER TO postgres;

--
-- TOC entry 202 (class 1259 OID 16412)
-- Name: meeting_id_meeting_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.meeting_id_meeting_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.meeting_id_meeting_seq OWNER TO postgres;

--
-- TOC entry 2248 (class 0 OID 0)
-- Dependencies: 202
-- Name: meeting_id_meeting_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.meeting_id_meeting_seq OWNED BY public.meeting.id_meeting;


--
-- TOC entry 205 (class 1259 OID 16425)
-- Name: relationship; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relationship (
    id_relationship bigint NOT NULL,
    note character varying(200),
    id_relationship_type bigint,
    id_user1 bigint,
    id_user2 bigint
);


ALTER TABLE public.relationship OWNER TO postgres;

--
-- TOC entry 204 (class 1259 OID 16423)
-- Name: relationship_id_relationship_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.relationship_id_relationship_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.relationship_id_relationship_seq OWNER TO postgres;

--
-- TOC entry 2249 (class 0 OID 0)
-- Dependencies: 204
-- Name: relationship_id_relationship_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.relationship_id_relationship_seq OWNED BY public.relationship.id_relationship;


--
-- TOC entry 207 (class 1259 OID 16433)
-- Name: relationship_type; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.relationship_type (
    id_relationship_type bigint NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.relationship_type OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16431)
-- Name: relationship_type_id_relationship_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.relationship_type_id_relationship_type_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.relationship_type_id_relationship_type_seq OWNER TO postgres;

--
-- TOC entry 2250 (class 0 OID 0)
-- Dependencies: 206
-- Name: relationship_type_id_relationship_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.relationship_type_id_relationship_type_seq OWNED BY public.relationship_type.id_relationship_type;


--
-- TOC entry 209 (class 1259 OID 16441)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id_role bigint NOT NULL,
    title character varying(255) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16447)
-- Name: role_has_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role_has_user (
    id_role bigint NOT NULL,
    id_user bigint NOT NULL
);


ALTER TABLE public.role_has_user OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16439)
-- Name: role_id_role_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.role_id_role_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.role_id_role_seq OWNER TO postgres;

--
-- TOC entry 2251 (class 0 OID 0)
-- Dependencies: 208
-- Name: role_id_role_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.role_id_role_seq OWNED BY public.role.id_role;


--
-- TOC entry 212 (class 1259 OID 16454)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id_user bigint NOT NULL,
    age integer,
    birthday date,
    email character varying(100) NOT NULL,
    first_name character varying(45) NOT NULL,
    nickname character varying(45) NOT NULL,
    password character varying(255) NOT NULL,
    surname character varying(45) NOT NULL,
    id_address bigint
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16463)
-- Name: user_has_meeting; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.user_has_meeting (
    id_user bigint NOT NULL,
    id_meeting bigint NOT NULL
);


ALTER TABLE public.user_has_meeting OWNER TO postgres;

--
-- TOC entry 211 (class 1259 OID 16452)
-- Name: user_id_user_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.user_id_user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.user_id_user_seq OWNER TO postgres;

--
-- TOC entry 2252 (class 0 OID 0)
-- Dependencies: 211
-- Name: user_id_user_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.user_id_user_seq OWNED BY public."user".id_user;


--
-- TOC entry 2076 (class 2604 OID 16393)
-- Name: address id_address; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address ALTER COLUMN id_address SET DEFAULT nextval('public.address_id_address_seq'::regclass);


--
-- TOC entry 2077 (class 2604 OID 16401)
-- Name: contact id_contact; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact ALTER COLUMN id_contact SET DEFAULT nextval('public.contact_id_contact_seq'::regclass);


--
-- TOC entry 2078 (class 2604 OID 16409)
-- Name: contact_type id_contact_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_type ALTER COLUMN id_contact_type SET DEFAULT nextval('public.contact_type_id_contact_type_seq'::regclass);


--
-- TOC entry 2079 (class 2604 OID 16417)
-- Name: meeting id_meeting; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting ALTER COLUMN id_meeting SET DEFAULT nextval('public.meeting_id_meeting_seq'::regclass);


--
-- TOC entry 2080 (class 2604 OID 16428)
-- Name: relationship id_relationship; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship ALTER COLUMN id_relationship SET DEFAULT nextval('public.relationship_id_relationship_seq'::regclass);


--
-- TOC entry 2081 (class 2604 OID 16436)
-- Name: relationship_type id_relationship_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship_type ALTER COLUMN id_relationship_type SET DEFAULT nextval('public.relationship_type_id_relationship_type_seq'::regclass);


--
-- TOC entry 2082 (class 2604 OID 16444)
-- Name: role id_role; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role ALTER COLUMN id_role SET DEFAULT nextval('public.role_id_role_seq'::regclass);


--
-- TOC entry 2083 (class 2604 OID 16457)
-- Name: user id_user; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user" ALTER COLUMN id_user SET DEFAULT nextval('public.user_id_user_seq'::regclass);


--
-- TOC entry 2085 (class 2606 OID 16395)
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id_address);


--
-- TOC entry 2087 (class 2606 OID 16403)
-- Name: contact contact_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT contact_pkey PRIMARY KEY (id_contact);


--
-- TOC entry 2089 (class 2606 OID 16411)
-- Name: contact_type contact_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_type
    ADD CONSTRAINT contact_type_pkey PRIMARY KEY (id_contact_type);


--
-- TOC entry 2093 (class 2606 OID 16422)
-- Name: meeting meeting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.meeting
    ADD CONSTRAINT meeting_pkey PRIMARY KEY (id_meeting);


--
-- TOC entry 2095 (class 2606 OID 16430)
-- Name: relationship relationship_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship
    ADD CONSTRAINT relationship_pkey PRIMARY KEY (id_relationship);


--
-- TOC entry 2097 (class 2606 OID 16438)
-- Name: relationship_type relationship_type_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship_type
    ADD CONSTRAINT relationship_type_pkey PRIMARY KEY (id_relationship_type);


--
-- TOC entry 2103 (class 2606 OID 16451)
-- Name: role_has_user role_has_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_has_user
    ADD CONSTRAINT role_has_user_pkey PRIMARY KEY (id_role, id_user);


--
-- TOC entry 2101 (class 2606 OID 16446)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id_role);


--
-- TOC entry 2091 (class 2606 OID 16469)
-- Name: contact_type uk_1fq45jis28gt2u2qt757e04vy; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact_type
    ADD CONSTRAINT uk_1fq45jis28gt2u2qt757e04vy UNIQUE (title);


--
-- TOC entry 2099 (class 2606 OID 16471)
-- Name: relationship_type uk_r42xwql4glcbqp0y3r3mo4tac; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship_type
    ADD CONSTRAINT uk_r42xwql4glcbqp0y3r3mo4tac UNIQUE (title);


--
-- TOC entry 2107 (class 2606 OID 16467)
-- Name: user_has_meeting user_has_meeting_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_meeting
    ADD CONSTRAINT user_has_meeting_pkey PRIMARY KEY (id_user, id_meeting);


--
-- TOC entry 2105 (class 2606 OID 16462)
-- Name: user user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (id_user);


--
-- TOC entry 2114 (class 2606 OID 16502)
-- Name: role_has_user fk1prjjyp0ypvod7oik3kmrg82v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_has_user
    ADD CONSTRAINT fk1prjjyp0ypvod7oik3kmrg82v FOREIGN KEY (id_role) REFERENCES public.role(id_role);


--
-- TOC entry 2110 (class 2606 OID 16482)
-- Name: relationship fk3b7pcnfgfdblhublf8t8dvn9l; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship
    ADD CONSTRAINT fk3b7pcnfgfdblhublf8t8dvn9l FOREIGN KEY (id_relationship_type) REFERENCES public.relationship_type(id_relationship_type);


--
-- TOC entry 2109 (class 2606 OID 16477)
-- Name: contact fk9u1accat4pp2y4oc1c517w9x5; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT fk9u1accat4pp2y4oc1c517w9x5 FOREIGN KEY (id_user) REFERENCES public."user"(id_user);


--
-- TOC entry 2113 (class 2606 OID 16497)
-- Name: role_has_user fkb6kfs1qlass0pypxxo70kryfh; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role_has_user
    ADD CONSTRAINT fkb6kfs1qlass0pypxxo70kryfh FOREIGN KEY (id_user) REFERENCES public."user"(id_user);


--
-- TOC entry 2115 (class 2606 OID 16507)
-- Name: user fke9ip6lgtvlukkgld3d4e89puj; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT fke9ip6lgtvlukkgld3d4e89puj FOREIGN KEY (id_address) REFERENCES public.address(id_address);


--
-- TOC entry 2116 (class 2606 OID 16512)
-- Name: user_has_meeting fkml9yy10r3vs04q5svigh6xhud; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_meeting
    ADD CONSTRAINT fkml9yy10r3vs04q5svigh6xhud FOREIGN KEY (id_meeting) REFERENCES public."user"(id_user);


--
-- TOC entry 2117 (class 2606 OID 16517)
-- Name: user_has_meeting fkoty72b7w06cg3rjb4twj2bdqx; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.user_has_meeting
    ADD CONSTRAINT fkoty72b7w06cg3rjb4twj2bdqx FOREIGN KEY (id_user) REFERENCES public.meeting(id_meeting);


--
-- TOC entry 2111 (class 2606 OID 16487)
-- Name: relationship fkq3kknqkoh2j8dvm20n6g2m1kk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship
    ADD CONSTRAINT fkq3kknqkoh2j8dvm20n6g2m1kk FOREIGN KEY (id_user1) REFERENCES public."user"(id_user);


--
-- TOC entry 2112 (class 2606 OID 16492)
-- Name: relationship fksk20i68u5s98osmdrispa9bt2; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.relationship
    ADD CONSTRAINT fksk20i68u5s98osmdrispa9bt2 FOREIGN KEY (id_user2) REFERENCES public."user"(id_user);


--
-- TOC entry 2108 (class 2606 OID 16472)
-- Name: contact fkx0wbmi3et03b7xwys8sa0d7; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.contact
    ADD CONSTRAINT fkx0wbmi3et03b7xwys8sa0d7 FOREIGN KEY (id_contact_type) REFERENCES public.contact_type(id_contact_type);


-- Completed on 2018-09-08 14:21:33

--
-- PostgreSQL database dump complete
--

