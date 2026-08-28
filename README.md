[1mdiff --git a/README.md b/README.md[m
[1mindex 8e2c58d..bb3cb44 100644[m
[1m--- a/README.md[m
[1m+++ b/README.md[m
[36m@@ -1,2617 +1,27 @@[m
[31m-Wednesday 5:50 PM[m
[31m-Tech Lead Assessment Test (2).pdf[m
[31m-PDF[m
[31m-Can you explain what are the requirements with the bread about assessment. How much time it's takes. It matches my profile or not every thing[m
[31m-[m
[31m-Yes. I went through the entire 8-page Datastraw Technologies assessment. This is much bigger than a normal coding assignment, so I’ll break down exactly what they expect, how much time it realistically takes, and whether it matches your current profile.[m
[31m-[m
[31m-1. What is this assessment actually for?[m
[31m-[m
[31m-The assessment is for a Tech Lead / future technology leadership type of role, not a normal Java/Spring Boot developer internship.[m
[31m-[m
[31m-They explicitly say they want someone who can eventually build and lead a technology team, although they don't require previous formal management experience.[m
[31m-[m
[31m-The assessment has 4 parts:[m
[31m-[m
[31m-Part	What you have to do	Difficulty for you[m
[31m-1. Build	Build an AI-powered CX Reply Assistant	🟠 Medium–Hard[m
[31m-2. System Design	Design the same system for 500 brands / millions of messages	🔴 Hard[m
[31m-3. Problem Solving	Answer AI cost/reliability scenario	🟠 Medium[m
[31m-4. Leadership	Answer 5 leadership/ownership questions	🟠 Medium[m
[31m-[m
[31m-They officially estimate 10–12 hours of effort and give a 3–4 day submission deadline.[m
[31m-[m
[31m-But for your current skill level, I would realistically budget around 15–20 hours, because you'll need to learn some things while building.[m
[31m-[m
[31m-2. PART 1 — Build the AI Application[m
[31m-[m
[31m-This is the biggest part.[m
[31m-[m
[31m-They want a working deployed application, not just code running on your laptop.[m
[31m-[m
[31m-The application is basically:[m
[31m-[m
[31m-Customer[m
[31m-↓[m
[31m-Customer conversation[m
[31m-↓[m
[31m-CX Agent[m
[31m-↓[m
[31m-Your Application[m
[31m-↓[m
[31m-Retrieve Brand Policies[m
[31m-↓[m
[31m-AI / LLM[m
[31m-↓[m
[31m-Suggested Reply[m
[31m-↓[m
[31m-Agent edits[m
[31m-↓[m
[31m-Agent approves[m
[31m-Your UI needs[m
[31m-[m
[31m-You need to show:[m
[31m-[m
[31m-Customer name[m
[31m-Brand[m
[31m-Conversation history[m
[31m-Latest customer message[m
[31m-Basic order information[m
[31m-[m
[31m-Mock data is completely acceptable.[m
[31m-[m
[31m-For example:[m
[31m-[m
[31m-Customer: Rahul[m
[31m-Brand: XYZ Store[m
[31m-[m
[31m-Conversation:[m
[31m-Customer: My order was delivered[m
[31m-but the bottle is broken.[m
[31m-[m
[31m-Order:[m
[31m-Order ID: #12345[m
[31m-Status: Delivered[m
[31m-Delivery Date: 25 Aug 2026[m
[31m-[m
[31m-[ Generate Reply ][m
[31m-3. You need a Knowledge Base[m
[31m-[m
[31m-This is important because this is essentially a RAG-style AI application.[m
[31m-[m
[31m-You need brand information containing:[m
[31m-[m
[31m-Return Policy[m
[31m-Refund Policy[m
[31m-Shipping Policy[m
[31m-Cancellation Policy[m
[31m-[m
[31m-The system must retrieve the relevant information when generating the answer.[m
[31m-[m
[31m-For example:[m
[31m-[m
[31m-Customer says:[m
[31m-[m
[31m-My bottle arrived broken. What can I do?[m
[31m-[m
[31m-Your application retrieves:[m
[31m-[m
[31m-Refund Policy:[m
[31m-Damaged products can be refunded[m
[31m-within 7 days of delivery.[m
[31m-[m
[31m-Return Policy:[m
[31m-Damaged products are eligible[m
[31m-for replacement.[m
[31m-[m
[31m-Then sends that context to the LLM.[m
[31m-[m
[31m-4. AI Reply Generation[m
[31m-[m
[31m-When the agent clicks:[m
[31m-[m
[31m-Generate Reply[m
[31m-[m
[31m-your system needs to:[m
[31m-[m
[31m-1. Identify brand[m
[31m-   ↓[m
[31m-2. Retrieve relevant knowledge[m
[31m-   ↓[m
[31m-3. Build context[m
[31m-   ↓[m
[31m-4. Send context + conversation to LLM[m
[31m-   ↓[m
[31m-5. Generate response[m
[31m-   ↓[m
[31m-6. Show response to agent[m
[31m-[m
[31m-The agent must then be able to:[m
[31m-[m
[31m-Edit[m
[31m-Regenerate[m
[31m-Approve[m
[31m-[m
[31m-Actual WhatsApp/email sending is NOT required.[m
[31m-[m
[31m-This part actually matches your recent Spring AI learning.[m
[31m-[m
[31m-You've already been working on Spring AI, so concepts like:[m
[31m-[m
[31m-Application → Prompt → LLM → Response[m
[31m-[m
[31m-are directly relevant.[m
[31m-[m
[31m-5. AI Guardrails[m
[31m-[m
[31m-This is one of the things they will probably pay serious attention to.[m
[31m-[m
[31m-Suppose the knowledge base says:[m
[31m-[m
[31m-Refund allowed only within 7 days.[m
[31m-[m
[31m-Customer says:[m
[31m-[m
[31m-I received this 20 days ago.[m
[31m-Can I get a refund?[m
[31m-[m
[31m-Your AI shouldn't say:[m
[31m-[m
[31m-Yes, you will definitely receive a refund.[m
[31m-[m
[31m-Instead, it should recognize that the available policy doesn't permit confidently promising that outcome.[m
[31m-[m
[31m-The assessment specifically asks you to think about what the AI should do when information is unavailable.[m
[31m-[m
[31m-A good implementation could produce something like:[m
[31m-[m
[31m-"I'm sorry about the issue. Based on the available refund policy, refunds are generally available within 7 days of delivery. Since your order was delivered 20 days ago, I'll need to verify whether an exception can be made."[m
[31m-[m
[31m-That demonstrates grounded AI instead of blindly trusting the LLM.[m
[31m-[m
[31m-6. Data & Logging[m
[31m-[m
[31m-You also need to store:[m
[31m-[m
[31m-Customer message[m
[31m-↓[m
[31m-Retrieved knowledge[m
[31m-↓[m
[31m-AI generated response[m
[31m-↓[m
[31m-Agent edited response[m
[31m-↓[m
[31m-Final response[m
[31m-↓[m
[31m-Timestamp[m
[31m-[m
[31m-They explicitly request these records.[m
[31m-[m
[31m-This is where your database/backend knowledge becomes useful.[m
[31m-[m
[31m-7. What technologies do they expect?[m
[31m-[m
[31m-Interestingly, they give you freedom.[m
[31m-[m
[31m-Their existing environment includes:[m
[31m-[m
[31m-React[m
[31m-Supabase[m
[31m-PostgreSQL[m
[31m-Supabase Auth[m
[31m-Edge Functions / RPCs[m
[31m-Qdrant[m
[31m-OpenRouter[m
[31m-DeepInfra[m
[31m-REST APIs[m
[31m-Webhooks[m
[31m-Meta WhatsApp API[m
[31m-[m
[31m-But they explicitly say you don't need to use all of them. They care more about your engineering decisions.[m
[31m-[m
[31m-This is important for YOU.[m
[31m-[m
[31m-You don't need to suddenly learn:[m
[31m-[m
[31m-React + Supabase + Qdrant + OpenRouter + WhatsApp API + everything else.[m
[31m-[m
[31m-That would waste your time.[m
[31m-[m
[31m-You can build a sensible stack around what you already know.[m
[31m-[m
[31m-For example:[m
[31m-[m
[31m-Frontend[m
[31m-↓[m
[31m-Spring Boot REST API[m
[31m-↓[m
[31m-PostgreSQL[m
[31m-↓[m
[31m-Knowledge Retrieval[m
[31m-↓[m
[31m-Spring AI[m
[31m-↓[m
[31m-LLM[m
[31m-[m
[31m-That would actually allow you to leverage your Java + Spring Boot + Spring AI learning.[m
[31m-[m
[31m-8. PART 2 — System Design[m
[31m-[m
[31m-This is probably the weakest area relative to your current profile, but it doesn't mean you cannot attempt it.[m
[31m-[m
[31m-They give you this scenario:[m
[31m-[m
[31m-500 brands[m
[31m-5,000 CX agents[m
[31m-Millions of messages[m
[31m-Multiple communication channels[m
[31m-AI responses[m
[31m-Brand-specific knowledge bases[m
[31m-[m
[31m-Then you have to design the architecture.[m
[31m-[m
[31m-You need one architecture diagram and a maximum 2–3 page explanation.[m
[31m-[m
[31m-You need to discuss:[m
[31m-[m
[31m-Frontend[m
[31m-Backend/API[m
[31m-Database[m
[31m-Authentication[m
[31m-External integrations[m
[31m-AI layer[m
[31m-Knowledge retrieval[m
[31m-Queues/background jobs[m
[31m-[m
[31m-9. Multi-tenant security[m
[31m-[m
[31m-This is another important concept.[m
[31m-[m
[31m-Imagine:[m
[31m-[m
[31m-Brand A[m
[31m-├── Customers[m
[31m-├── Orders[m
[31m-└── Knowledge[m
[31m-[m
[31m-Brand B[m
[31m-├── Customers[m
[31m-├── Orders[m
[31m-└── Knowledge[m
[31m-[m
[31m-Brand A must never accidentally see Brand B's data.[m
[31m-[m
[31m-They specifically ask:[m
[31m-[m
[31m-How would you ensure Brand A's data, conversations, and knowledge can never accidentally be accessed by Brand B?[m
[31m-[m
[31m-And where would you enforce it.[m
[31m-[m
[31m-This is basically multi-tenancy + authorization + data isolation.[m
[31m-[m
[31m-10. AI architecture[m
[31m-[m
[31m-You also have to explain:[m
[31m-[m
[31m-Knowledge retrieval[m
[31m-Hallucination prevention[m
[31m-Context management[m
[31m-Confidence/fallback[m
[31m-AI evaluation[m
[31m-[m
[31m-Your Spring AI learning gives you a starting point here, but AI system design at scale is a more advanced topic.[m
[31m-[m
[31m-11. Scalability & Reliability[m
[31m-[m
[31m-They'll ask what happens when:[m
[31m-[m
[31m-20 brands[m
[31m-↓[m
[31m-500 brands[m
[31m-[m
[31m-What breaks first?[m
[31m-[m
[31m-They also give real production failure scenarios:[m
[31m-[m
[31m-Duplicate webhook[m
[31m-Webhook arrives[m
[31m-Webhook arrives AGAIN[m
[31m-[m
[31m-You need to explain idempotency.[m
[31m-[m
[31m-External API timeout[m
[31m-[m
[31m-You need to discuss:[m
[31m-[m
[31m-timeout[m
[31m-retry[m
[31m-backoff[m
[31m-failure handling[m
[31m-AI request fails[m
[31m-[m
[31m-You need:[m
[31m-[m
[31m-retry/fallback/error handling[m
[31m-Message processed but response wasn't sent[m
[31m-[m
[31m-You need to think about:[m
[31m-[m
[31m-message status[m
[31m-retry[m
[31m-queue[m
[31m-dead-letter handling[m
[31m-[m
[31m-They explicitly say you don't have to build these mechanisms; they want to see how you think about them.[m
[31m-[m
[31m-12. PART 3 — Technical Problem Solving[m
[31m-[m
[31m-This is a written answer.[m
[31m-[m
[31m-Scenario:[m
[31m-[m
[31m-AI costs increased from:[m
[31m-[m
[31m-₹20,000/month → ₹1,00,000/month[m
[31m-[m
[31m-while customer/conversation volume increased only about 40%.[m
[31m-[m
[31m-They ask:[m
[31m-[m
[31m-What would you investigate and what would you potentially change?[m
[31m-[m
[31m-They expect you to think about:[m
[31m-[m
[31m-Model selection[m
[31m-Token usage[m
[31m-Prompt size[m
[31m-Retrieval[m
[31m-Caching[m
[31m-Unnecessary AI calls[m
[31m-Monitoring[m
[31m-Rate limits[m
[31m-Architecture[m
[31m-[m
[31m-This isn't really a coding question.[m
[31m-[m
[31m-It's testing whether you can debug a production AI system logically.[m
[31m-[m
[31m-13. PART 4 — Leadership[m
[31m-[m
[31m-This is where the assessment becomes very different from a normal developer assignment.[m
[31m-[m
[31m-They ask:[m
[31m-[m
[31m-Leadership[m
[31m-[m
[31m-Have you led/mentored someone?[m
[31m-[m
[31m-Giving feedback[m
[31m-[m
[31m-A junior developer repeatedly writes poorly structured code.[m
[31m-[m
[31m-What do you do?[m
[31m-[m
[31m-Disagreement[m
[31m-[m
[31m-A developer disagrees strongly with your architecture decision.[m
[31m-[m
[31m-How do you handle it?[m
[31m-[m
[31m-Mistake[m
[31m-[m
[31m-Tell them about a technical mistake that affected someone/business.[m
[31m-[m
[31m-First 30 days[m
[31m-[m
[31m-You join a company with:[m
[31m-[m
[31m-messy processes[m
[31m-incomplete documentation[m
[31m-unclear workflows[m
[31m-[m
[31m-What would you do during your first 30 days?[m
[31m-[m
[31m-They explicitly say formal management experience isn't required. If you haven't managed people, you can talk about helping a less-experienced teammate.[m
[31m-[m
[31m-14. AI usage disclosure[m
[31m-[m
[31m-This is actually good news.[m
[31m-[m
[31m-They encourage using AI.[m
[31m-[m
[31m-They specifically allow:[m
[31m-[m
[31m-ChatGPT[m
[31m-Claude[m
[31m-Cursor[m
[31m-GitHub Copilot[m
[31m-Gemini[m
[31m-Stack Overflow[m
[31m-Documentation[m
[31m-etc.[m
[31m-[m
[31m-But you need to explain:[m
[31m-[m
[31m-Which AI tools you used[m
[31m-How you used them[m
[31m-2–3 examples[m
[31m-Something AI got wrong and how you identified/fixed it[m
[31m-[m
[31m-So using me to help you understand/build this assignment is not against their rules.[m
[31m-[m
[31m-The important part is that you must actually understand what you submit.[m
[31m-[m
[31m-15. What exactly do you submit?[m
[31m-[m
[31m-This is the final deliverable list:[m
[31m-[m
[31m-1. Deployed application[m
[31m-[m
[31m-A publicly accessible URL.[m
[31m-[m
[31m-2. GitHub repository[m
[31m-[m
[31m-Must contain:[m
[31m-[m
[31m-Source code[m
[31m-README[m
[31m-Setup instructions[m
[31m-.env.example[m
[31m-Database/schema information[m
[31m-Clean folder structure[m
[31m-3. Architecture diagram[m
[31m-4. Architecture document[m
[31m-[m
[31m-Maximum 2–3 pages.[m
[31m-[m
[31m-5. Demo video[m
[31m-[m
[31m-Maximum 5 minutes.[m
[31m-[m
[31m-It needs to cover:[m
[31m-[m
[31m-What you built[m
[31m-How it works[m
[31m-Architecture[m
[31m-Technical decisions[m
[31m-AI implementation[m
[31m-One improvement[m
[31m-6. Written answers[m
[31m-[m
[31m-Parts 3 and 4 in a Word document.[m
[31m-[m
[31m-All submitted through their Google Form.[m
[31m-[m
[31m-16. Now the important question — DOES IT MATCH YOUR PROFILE?[m
[31m-[m
[31m-Based on the projects and learning you've been doing recently:[m
[31m-[m
[31m-Your strengths[m
[31m-[m
[31m-You already have exposure to:[m
[31m-[m
[31m-Java[m
[31m-⬇️[m
[31m-Spring Boot[m
[31m-⬇️[m
[31m-REST APIs[m
[31m-⬇️[m
[31m-Database/backend development[m
[31m-⬇️[m
[31m-Spring AI / LLM concepts[m
[31m-[m
[31m-That gives you a good foundation for Part 1.[m
[31m-[m
[31m-Your Spring AI project is particularly relevant because this assessment revolves around an AI-powered application.[m
[31m-[m
[31m-Your weaker areas[m
[31m-[m
[31m-Where I'd expect you to need preparation:[m
[31m-[m
[31m-🔴 1. System Design[m
[31m-[m
[31m-This assessment expects you to think at:[m
[31m-[m
[31m-500 brands[m
[31m-5,000 agents[m
[31m-millions of messages[m
[31m-[m
[31m-That's more advanced than simply building Spring Boot CRUD APIs.[m
[31m-[m
[31m-🔴 2. RAG / Vector Database[m
[31m-[m
[31m-They mention Qdrant and knowledge retrieval.[m
[31m-[m
[31m-You understand the general AI direction, but implementing a production-quality RAG architecture is a step up.[m
[31m-[m
[31m-🟠 3. React[m
[31m-[m
[31m-Their existing environment uses React.[m
[31m-[m
[31m-But fortunately they don't require you to use their exact stack.[m
[31m-[m
[31m-🟠 4. Deployment[m
[31m-[m
[31m-They require a publicly accessible deployed application.[m
[31m-[m
[31m-So local-only development isn't enough.[m
[31m-[m
[31m-🟠 5. Leadership/system thinking[m
[31m-[m
[31m-Your current learning seems much more developer-focused than Tech Lead-focused.[m
[31m-[m
[31m-That's okay because the document itself says they aren't expecting someone with 2 years of experience to have already done everything.[m
[31m-[m
[31m-17. My honest suitability score for YOU[m
[31m-[m
[31m-I'd rate it approximately:[m
[31m-[m
[31m-Area	Your current fit[m
[31m-Java	⭐⭐⭐⭐☆[m
[31m-Spring Boot	⭐⭐⭐⭐☆[m
[31m-REST API	⭐⭐⭐⭐☆[m
[31m-Database	⭐⭐⭐⭐☆[m
[31m-Spring AI	⭐⭐⭐☆☆[m
[31m-LLM integration	⭐⭐⭐☆☆[m
[31m-RAG	⭐⭐☆☆☆[m
[31m-React/frontend	⭐⭐☆☆☆[m
[31m-System Design	⭐⭐☆☆☆[m
[31m-Scalability	⭐⭐☆☆☆[m
[31m-Production architecture	⭐⭐☆☆☆[m
[31m-Leadership	⭐⭐☆☆☆[m
[31m-Overall:[m
[31m-[m
[31m-Current technical fit: ~55–65%[m
[31m-[m
[31m-But importantly:[m
[31m-[m
[31m-Assessment difficulty ≠ your eligibility.[m
[31m-[m
[31m-The company itself says they are evaluating potential to grow into the role, not whether you have already done everything.[m
[31m-[m
[31m-18. Should you attempt it?[m
[31m-    My answer: YES — but don't treat it like a 10-hour assignment.[m
[31m-[m
[31m-For you, I'd target:[m
[31m-[m
[31m-Day 1[m
[31m-Backend + database + basic UI[m
[31m-↓[m
[31m-Day 2[m
[31m-Spring AI + knowledge retrieval + guardrails[m
[31m-↓[m
[31m-Day 3[m
[31m-Testing + deployment + architecture diagram[m
[31m-↓[m
[31m-Day 4[m
[31m-System design + written answers + demo video[m
[31m-[m
[31m-The company's official estimate is 10–12 hours, but because some areas are newer to you, 15–20 focused hours is a safer expectation.[m
[31m-[m
[31m-19. The biggest mistake you should NOT make[m
[31m-[m
[31m-Don't try to build:[m
[31m-[m
[31m-WhatsApp integration[m
[31m-+ Meta API[m
[31m-+ Qdrant[m
[31m-+ Supabase[m
[31m-+ React[m
[31m-+ complex authentication[m
[31m-+ microservices[m
[31m-+ Kubernetes[m
[31m-+ huge database[m
[31m-+ multiple AI models[m
[31m-[m
[31m-just because those technologies appear in the PDF.[m
[31m-[m
[31m-They explicitly say:[m
[31m-[m
[31m-You will score higher for one well-designed, well-understood solution than for adding lots of unnecessary features.[m
[31m-[m
[31m-That's actually very favorable for you.[m
[31m-[m
[31m-My recommended approach for your profile[m
[31m-[m
[31m-I would build a clean Spring Boot + Spring AI project rather than trying to copy Datastraw's entire technology stack:[m
[31m-[m
[31m-                  ┌─────────────────┐[m
[31m-                  │   CX Agent UI   │[m
[31m-                  └────────┬────────┘[m
[31m-                           ↓[m
[31m-                  ┌─────────────────┐[m
[31m-                  │ Spring Boot API │[m
[31m-                  └───────┬─────────┘[m
[31m-                          ↓[m
[31m-          ┌───────────────┼────────────────┐[m
[31m-          ↓               ↓                ↓[m
[31m-Conversation DB   Knowledge Base    Order Data[m
[31m-│               │[m
[31m-│               ↓[m
[31m-│        Retrieval/RAG[m
[31m-│               │[m
[31m-└───────────────┼───────────────┘[m
[31m-↓[m
[31m-┌──────────┐[m
[31m-│ Spring AI│[m
[31m-└────┬─────┘[m
[31m-↓[m
[31m-LLM[m
[31m-↓[m
[31m-Suggested Reply[m
[31m-↓[m
[31m-Edit → Approve → Log[m
[31m-[m
[31m-That lets you use what you're already learning instead of starting completely from zero.[m
[31m-[m
[31m-Bottom line[m
[31m-[m
[31m-Yes, you can attempt this. It is above your current level in system design and leadership, but the actual application portion is closely related to the Spring Boot + Spring AI direction you've been working on. The assessment itself says they value potential, ownership, learning ability, and a well-understood solution over knowing every technology listed.[m
[31m-[m
[31m-If you want to submit this, I would not start coding immediately. First we should break the assignment into a very specific 3–4 day execution plan, decide the exact technology stack, database tables, AI/RAG approach, architecture diagram, and then build it step-by-step.[m
[31m-[m
[31m-next[m
[31m-Pasted text(20260826-155840).txt[m
[31m-Document[m
[31m-Pasted text(20260826-160126).txt[m
[31m-Document[m
[31m-Pasted text(20260826-160958).txt[m
[31m-Document[m
[31m-c