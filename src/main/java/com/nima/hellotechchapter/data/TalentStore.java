package com.nima.hellotechchapter.data;

import com.nima.hellotechchapter.model.Document;
import com.nima.hellotechchapter.model.Talent;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class TalentStore {

    private static final String NIMA_ID    = "a1b2c3d4-e5f6-7890-abcd-ef1234567890";
    private static final String ANDREAS_ID = "b2c3d4e5-f6a7-8901-bcde-f12345678901";

    private final List<Talent> talents = List.of(
            new Talent(
                    NIMA_ID,
                    "Nima Salami",
                    "Datamatiker-studerende | DevOps & infrastruktur",
                    "4. semester datamatiker på KEA med specialisering i DevOps, infrastruktur og automatisering. " +
                            "Drifter egne produktionskørende servere i fritiden – ikke fordi det er et krav, men fordi jeg vil forstå systemerne i dybden. " +
                            "Baggrund i ledelse og drift, hvor ansvar og overblik var en forudsætning. " +
                            "Jeg venter ikke på at få en opgave tildelt – jeg finder ud af hvad der skal løses.",
                    "nima@nimasalami.dk",
                    "+45 22 98 50 77",
                    "København NV",
                    "Danmark",
                    "https://github.com/hajisan",
                    "https://www.linkedin.com/in/nima-salami-41197744/"
            ),
            new Talent(
                    ANDREAS_ID,
                    "Andreas Gabel",
                    "Datamatiker-studerende | DevOps & Backend",
                    "4. semester datamatiker på EK med fokus på DevOps, backend-udvikling og automatisering. Jeg bygger og drifter min egen cloud-native platform på andreasgabel.dk — Spring Boot, Docker, GitHub Actions CI/CD/CD/CF og Nginx på en DigitalOcean droplet. Fire projekter kører i produktion bag én reverse proxy med HTTPS, komplet med JaCoCo, Checkstyle, SpotBugs, Trivy og frontend linting. Har baggrund fra IT-operations hos Københavns Kommune, hvor jeg automatiserer drift af Linux-baserede borger-PC'er.",
                    "andreassgabel@hotmail.com",
                    null,
                    "København",
                    "Danmark",
                    "https://github.com/Gabel1998",
                    "https://www.linkedin.com/in/andreas-søgaard-gabel-758991133"
            )
    );

    private final Map<String, List<Document>> documents = Map.of(
            NIMA_ID, List.of(
                    new Document(
                            "doc1-0000-0000-0000-000000000001",
                            "Motivationsbrev",
                            "Jeg søger praktikplads hos Tech Chapter i perioden august til slut oktober 2026. " +
                                    "Jeres specialisering i DevOps, SRE og Cloud Native er præcis den retning jeg arbejder mig hen imod – ikke kun på studiet, men i praksis med egne projekter. " +
                                    "Jeg drifter en selvhostet n8n-instans på DigitalOcean med Tailscale VPN, Cloudflare Tunnel og iptables, og jeg har bygget og deployeret en Spring Boot PWA med GitHub Actions CI/CD pipeline. " +
                                    "På 4. semester er jeg i gang med Kubernetes og Terraform som del af DevOps-faget – det er ikke teori for mig, det er en direkte forlængelse af det jeg laver i forvejen. " +
                                    "Det der tiltrækker mig ved Tech Chapter er ikke bare teknologistakken – det er tilgangen. " +
                                    "I arbejder med build-measure-learn og lean startup-metodik til at bygge et SaaS-produkt, og det er præcis det miljø hvor jeg vil lære hurtigst og bidrage mest. " +
                                    "Jeg kommer fra en baggrund med reel driftserfaring og ledelsesansvar, og jeg tager ansvar for det arbejde jeg leverer. " +
                                    "Praktik hos landets skarpeste DevOps-konsulenter er ikke et CV-punkt for mig – det er det rigtige sted at lære tingene ordentligt."
                    ),

                    new Document(
                            "doc2-0000-0000-0000-000000000002",
                            "CV",
                            "UDDANNELSE: Datamatiker, Erhvervsakademi København (EK), 2024–2027. " +
                                    "TECH STACK:" +
                                    "Sprog: Java, JavaScript, TypeScript, Python, Ruby, Bash. " +
                                    "Frameworks & frontend: Spring Boot, Spring Security, Spring JPA, FastAPI, Astro, Tailwind CSS, React. " +
                                    "DevOps & infrastruktur: Docker, Docker Compose, Watchtower, GitHub Actions CI/CD, nginx, " +
                                    "Linux (Ubuntu 24.04), Tailscale VPN, Cloudflare Tunnel, UFW, iptables/netfilter, Fail2ban, SSH key-only, cron jobs. " +
                                    "Cloud & hosting: DigitalOcean, Azure, Vercel. " +
                                    "Caching & messaging: Redis. " +
                                    "ML: TFLite (ai-edge-litert) – vegetableklassifikationsmodel deployed i produktion. " +
                                    "Automatisering & AI: n8n (selvhostet, produktionsklar), prompt engineering, AI-agenter, Claude Code subagenter. " +
                                    "Databaser: MySQL. " +
                                    "Andet: Git, GitHub Projects (kanban), SCRUM (Scrum Master i alle studieprojekter), OpenAPI Spec, linting. " +
                                    "Under oplæring (4. semester): Kubernetes, Terraform, avanceret CI/CD. " +
                                    "ERHVERVSERFARING: Juniorkonsulent IT og digital drift, We Do Democracy (2026–nu): " +
                                    "Microsoft 365 administration, PowerShell-automatisering, drift af Dropbox og Slack, migrering fra amerikanske til europæiske platforme. " +
                                    "Adm. medarbejder, Odense Kommune, Ukraine Enheden (2022–2023): " +
                                    "Organisationsudvikling, dataindsamling, personaleledelse, drift af indkvarteringscentre. " +
                                    "Produktionsmanager, Gameboks (2018–2020): Daglig drift, leverandørstyring, effektivisering af produktionslinje."
                    ),

                    new Document(
                            "doc3-0000-0000-0000-000000000003",
                            "Projekt: nimasalami.dk",
                            "LINK: https://www.nimasalami.dk | API: https://api.nimasalami.dk. " +
                                    "REPO: github.com/hajisan/nimasalami.dk (privat monorepo). " +
                                    "TYPE: Eget projekt – live i produktion. " +
                                    "BESKRIVELSE: Monorepo med Astro-frontend og FastAPI-backend. " +
                                    "Frontend: Astro, Tailwind CSS v3, TypeScript, React-komponenter – hosted på Vercel med auto-deploy fra main og security headers konfigureret i vercel.json. " +
                                    "Backend: FastAPI (Python 3.12), 2 Uvicorn workers, bag nginx reverse proxy med SSL via Let's Encrypt. " +
                                    "Hosted på DigitalOcean Droplet (Ubuntu 24.04). " +
                                    "Docker Compose med tre services: API-app, Redis (cache, TTL 300s) og Watchtower (auto-pull fra GHCR hvert 5. minut). " +
                                    "CI/CD: GitHub Actions bygger multi-stage image og pusher til GHCR ved push til api/**. " +
                                    "Multi-stage Dockerfile: stage 1 henter ML-model (veg.tflite) fra privat repo via git-lfs med token der aldrig ender i det endelige image. " +
                                    "ML-endpoint /scan: TFLite-inference med asyncio.Lock() for thread safety, HEIC/HEIF-understøttelse, 5 MB upload-cap og rate limiting via slowapi (20/min). " +
                                    "15-klasses vegetableklassifikator deployed som live demo på hjemmesiden. " +
                                    "Sikkerhed: UFW, Fail2ban, SSH key-only, CORS ikke wildcard, ALLOWED_REPOS-whitelist på GitHub-endpoint, " +
                                    "rate limiting på alle eksterne endpoints, nginx security headers. " +
                                    "Claude Code subagenter konfigureret for frontend-dev, content, security-auditing og API-udvikling. " +
                                    "STACK: Astro, TypeScript, Tailwind CSS, React, FastAPI, Python 3.12, Redis, Docker, Docker Compose, " +
                                    "Watchtower, GitHub Actions, nginx, Let's Encrypt, slowapi, Brevo (GDPR), TFLite, Vercel, DigitalOcean. " +
                                    "HVAD JEG LÆRTE: At designe en produktionsarkitektur hvor sikkerhed er bygget ind fra starten – " +
                                    "ikke som eftertanke. Multi-stage builds til at holde secrets ude af images, thread-safe inference, " +
                                    "input-whitelisting og automatiserede deploys der ikke kræver manuel indgriben."
                    ),

                    new Document(
                            "doc4-0000-0000-0000-000000000004",
                            "Projekt: GymRat",
                            "LINK: https://github.com/hajisan/gymrat. " +
                                    "TYPE: Eget projekt – deployed i produktion med live demo. " +
                                    "BESKRIVELSE: Webapp bygget som PWA til træningsregistrering. " +
                                    "Spring Boot backend, MySQL database, Docker med separate Compose-filer til dev og prod. " +
                                    "Deployed på egen DigitalOcean Droplet (Ubuntu 24.04) med GitHub Actions CI/CD pipeline og officielt releaset som v1. " +
                                    "Live demo tilgængeligt på nimasalami.dk/projects – kører på separat server fra portfolioens API. " +
                                    "STACK: Java, Spring Boot, Spring Security, Spring JPA, MySQL, Docker, Docker Compose, GitHub Actions, DigitalOcean. " +
                                    "HVAD JEG LÆRTE: Hele vejen fra lokal dev til produktionsserver – " +
                                    "Droplet-opsætning, DNS, reverse proxy, automatiseret build og deploy ved push til main, " +
                                    "og adskillelse af dev- og prod-miljøer i Compose."
                    ),

                    new Document(
                            "doc5-0000-0000-0000-000000000005",
                            "Projekt: n8n Server",
                            "TYPE: Eget projekt – produktionskørende. " +
                                    "BESKRIVELSE: Selvhostet n8n-instans på DigitalOcean Droplet (Ubuntu 24.04, Frankfurt). " +
                                    "Multi-layer netværkssikkerhed: Tailscale VPN med zero trust-adgang, " +
                                    "Cloudflare Tunnel til webhooks uden eksponerede porte, " +
                                    "iptables/netfilter der blokerer direkte IP-adgang, " +
                                    "Fail2ban mod SSH brute-force og SSH key-only login. " +
                                    "Automatisk opdatering via bash-script kaldt af cron job hver søndag kl. 03:00 med logging og versionskontrol. " +
                                    "STACK: Ubuntu 24.04, Docker, iptables, Tailscale, Cloudflare, Fail2ban, Bash, cron. " +
                                    "HVAD JEG LÆRTE: Zero trust-netværksarkitektur i praksis, firewall-hardening, " +
                                    "og at designe et system der vedligeholder sig selv uden manuel indgriben."
                    ),

                    new Document(
                            "doc6-0000-0000-0000-000000000006",
                            "Projekt: DevOps Legacy",
                            "LINK: https://github.com/nasOps/MonkKnows. " +
                                    "TYPE: Gruppeprojekt, studiet. " +
                                    "BESKRIVELSE: Legacy-kodebase i Python 2 migreret til Python 3 og rewritten til Ruby 3. " +
                                    "Arbejde med CI/CD-pipelines, DevOps-principper, linting og OpenAPI Spec-dokumentation. " +
                                    "STACK: Python, Ruby, GitHub Actions, OpenAPI Spec, linting. " +
                                    "HVAD JEG LÆRTE: At arbejde systematisk med en eksisterende kodebase under reelle CI/CD-krav, " +
                                    "og hvad det kræver at fastholde kodekvalitet og dokumentation i et team."
                    )

            )
    );

    public List<Talent> getAllTalents() {
        return talents;
    }

    public Optional<Talent> getTalentById(String id) {
        return talents.stream()
                .filter(talent -> talent.getId().equals(id))
                .findFirst();
    }

    public List<Document> getDocumentsByTalentId(String id) {
        return documents.getOrDefault(id, List.of());
    }

    public Optional<Document> getDocumentById(String talentId, String documentId) {
        return getDocumentsByTalentId(talentId).stream()
                .filter(d -> d.getId().equals(documentId))
                .findFirst();
    }
}
