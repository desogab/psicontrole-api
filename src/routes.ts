import { Router } from "express";

import { isAuthenticated } from "./middlewares/isAuthenticated";
import { AuthProfessionalUserController } from "./models/account/useCases/authProfessionalUser/AuthProfessionalUserController";
import { CreateClientController } from "./models/client/useCases/createClient/CreateClientController";
import { CreateProfessionalUseController } from "./models/professional/useCases/createProfessionalUser/CreateProfessionalUserController";

const routes = Router();

const createProfessionalUser = new CreateProfessionalUseController();
const authProfessionalUser = new AuthProfessionalUserController();

const createClient = new CreateClientController();

routes.post("/professional/create", createProfessionalUser.handle);
routes.post("/professional/auth", authProfessionalUser.handle);

routes.post("/client/create", isAuthenticated, createClient.handle);

export { routes };
