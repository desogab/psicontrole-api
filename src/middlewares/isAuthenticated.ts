import { NextFunction, Request, Response } from "express";
import { verify } from "jsonwebtoken";

interface IPayload {
  sub: string;
}

export async function isAuthenticated(
  request: Request,
  response: Response,
  next: NextFunction
) {
  const authHeader = request.headers.authorization;

  if (!authHeader)
    return response.status(401).json({ message: "Token is missing!" });

  const [, token] = authHeader.split(" ");

  try {
    const { sub } = verify(token, process.env.SECRET_JWT_TOKEN) as IPayload;

    request.professionalUserId = sub;

    return next();
  } catch (error) {
    return response.status(401).json({
      message: "Invalid Token!",
    });
  }
}
