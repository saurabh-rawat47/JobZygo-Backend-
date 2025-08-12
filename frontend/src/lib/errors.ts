export function getApiErrorMessage(error: unknown, fallback: string): string {
  if (typeof error === 'object' && error !== null) {
    const maybe = error as { response?: { data?: { message?: string } } };
    const msg = maybe.response?.data?.message;
    if (typeof msg === 'string' && msg.trim().length > 0) {
      return msg;
    }
  }
  return fallback;
}